package com.project.skin.category.service.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReOrderCategory {
	@Getter
	public static class OrderItem {
		private Long id;
		private Long newOrder;
	}

	@Getter
	@NoArgsConstructor
	public static class Request {
		List<OrderItem> orderItems;
	}

	@Getter
	@NoArgsConstructor
	public static class Response {
		List<Long> ids;

		@Builder
		public Response(List<Long> ids) {
			this.ids = ids;
		}
	}
}
