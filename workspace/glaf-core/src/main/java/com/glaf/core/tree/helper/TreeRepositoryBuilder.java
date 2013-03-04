/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glaf.core.tree.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.glaf.core.base.TreeModel;
import com.glaf.core.tree.component.TreeComponent;
import com.glaf.core.tree.component.TreeRepository;

public class TreeRepositoryBuilder {

	public TreeRepository build(List<TreeModel> treeModels) {
		Collections.sort(treeModels);
		List<TreeModel> nodes = new ArrayList<TreeModel>();
		Map<String, TreeModel> treeMap = new HashMap<String, TreeModel>();
		Map<Long, TreeModel> treeModelMap = new HashMap<Long, TreeModel>();

		for (int i = 0; i < treeModels.size(); i++) {
			TreeModel treeModel = (TreeModel) treeModels.get(i);
			if (treeModel.getId() == treeModel.getParentId()) {
				treeModel.setParentId(-1);
			}
			treeMap.put(String.valueOf(treeModel.getId()), treeModel);
			if (treeModel.getLocked() == 0) {
				nodes.add(treeModel);
			} else {
				treeModelMap.put(treeModel.getId(), treeModel);
			}
		}

		for (int j = 0; j < nodes.size(); j++) {
			TreeModel treeModel = nodes.get(j);
			if (treeModelMap.get(treeModel.getParentId()) != null) {
				treeModel.setLocked(1);
			}
		}

		TreeRepository repository = new TreeRepository();
		for (int i = 0; i < nodes.size(); i++) {
			TreeModel treeModel = nodes.get(i);
			if (treeModel.getLocked() != 0) {
				continue;
			}
			TreeComponent component = new TreeComponent();
			component.setId(String.valueOf(treeModel.getId()));
			component.setCode(treeModel.getCode());
			component.setTreeModel(treeModel);
			component.setTitle(treeModel.getName());
			component.setDescription(treeModel.getDescription());
			component.setLocation(treeModel.getUrl());
			component.setUrl(treeModel.getUrl());
			repository.addTree(component);

			String parentId = String.valueOf(treeModel.getParentId());
			if (StringUtils.isNotEmpty(parentId)
					&& treeMap.get(parentId) != null) {
				TreeComponent parentTree = repository.getTree(parentId);
				if (parentTree == null) {
					TreeModel parent = treeMap.get(parentId);
					parentTree = new TreeComponent();
					parentTree.setId(String.valueOf(parent.getId()));
					parentTree.setCode(parent.getCode());
					parentTree.setTitle(parent.getName());
					parentTree.setTreeModel(parent);
					repository.addTree(parentTree);
				}
				component.setParent(parentTree);
			}

		}
		return repository;
	}

}