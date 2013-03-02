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
package com.glaf.core.web.springmvc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.glaf.core.config.CustomProperties;
import com.glaf.core.config.SystemProperties;
import com.glaf.core.security.LoginContext;
import com.glaf.core.util.FileUtils;
import com.glaf.core.util.RequestUtils;

@Controller("/diskFileUploadJson")
@RequestMapping("/diskFileUploadJson")
public class MxDiskFileUploadJsonController {

	public final static String sp = System.getProperty("file.separator");

	private String getError(String message) throws Exception {
		JSONObject object = new JSONObject();
		object.put("error", 1);
		object.put("message", message);
		return object.toString();
	}

	@RequestMapping
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		// �ļ�����Ŀ¼·��
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "upload/" + loginContext.getActorId().hashCode() + "/";
		// �ļ�����Ŀ¼URL
		String saveUrl = request.getContextPath() + "/upload/"
				+ loginContext.getActorId().hashCode() + "/";
		// ���������ϴ����ļ���չ��
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
		// ����ļ���С
		long maxSize = 10240000;

		String allowSize = CustomProperties.getString("upload.maxSize");
		if (StringUtils.isEmpty(allowSize)) {
			allowSize = SystemProperties.getString("upload.maxSize");
		}

		if (StringUtils.isNotEmpty(allowSize)
				&& StringUtils.isNumeric(allowSize)) {
			maxSize = Long.parseLong(allowSize);
		}

		// ���Ŀ¼
		File uploadDir = new File(savePath);
		try {
			if (!uploadDir.exists()) {
				FileUtils.mkdirs(savePath);
			}
		} catch (Exception ex) {
		}

		if (!uploadDir.isDirectory()) {
			response.getWriter().write(getError("�ϴ�Ŀ¼�����ڡ�"));
			return;
		}
		// ���Ŀ¼дȨ��
		if (!uploadDir.canWrite()) {
			response.getWriter().write(getError("�ϴ�Ŀ¼û��дȨ�ޡ�"));
			return;
		}

		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

		Map<String, MultipartFile> fileMap = req.getFileMap();
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile mFile = entry.getValue();
			if (mFile.getOriginalFilename() != null && mFile.getSize() > 0) {
				// ����ļ���С
				if (mFile.getSize() > maxSize) {
					response.getWriter().write(getError("�ϴ��ļ���С�������ơ�"));
					return;
				}
				String fileName = mFile.getOriginalFilename();
				// �����չ��
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
					response.getWriter().write(getError("�ϴ��ļ���չ���ǲ���������չ����"));
					return;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(10000) + "." + fileExt;
				try {

					FileUtils.save(savePath + sp + newFileName,
							mFile.getInputStream());

				} catch (Exception ex) {
					ex.printStackTrace();
					response.getWriter().write(getError("�ϴ��ļ�ʧ�ܡ�"));
					return;
				}

				JSONObject object = new JSONObject();
				object.put("error", 0);
				object.put("url", saveUrl + newFileName);
				response.getWriter().write(object.toString());
			}
		}
	}

}