package com.project.skin.category.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateCategory {
	@Getter
	@NoArgsConstructor
	public static class Request {
		private String name;
		private String code;
		private Boolean showSkinFilter;
		private Long parentId;
	}

	@Getter
	public static class Response {
		private Long id;

		@Builder
		public Response(Long id) {
			this.id = id;
		}
	}
}
