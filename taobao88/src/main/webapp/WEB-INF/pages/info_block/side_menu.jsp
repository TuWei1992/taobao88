<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sideMenu != null}">
					<ul>
						<c:forEach items="${sideMenu}" var="menu">
							<li><a href="${menu.menuHref}" class="side_menu_parent" target="_blank">${menu.menuName}</a>
								<ul class="side_menu_children">
									<c:forEach items="${menu.children}" var="menu">
										<li><a href="${menu.menuHref}" target="_blank">${menu.menuName}</a>
											<ul class="side_menu_children">
												<c:forEach items="${menu.children}" var="menu">
													<li><a href="${menu.menuHref}" target="_blank">${menu.menuName}</a>
														<ul class="side_menu_children">
															<c:forEach items="${menu.children}" var="menu">
																<li><a href="${menu.menuHref}" target="_blank">${menu.menuName}</a>
															</c:forEach>
														</ul>
												</c:forEach>
											</ul>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
</c:if>