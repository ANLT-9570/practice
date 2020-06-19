<%@page pageEncoding="utf-8"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>


<c:forEach items="${list }" var="list">
					
	<c:if test="${empty list.children }">
		<c:if test="${list.generatemenu eq 1 }"><!-- //营造林辅助管理 -->
			<li class="home" id="home"><a href="javascript:void(0)" name="${list.url }" class="iframeurl" title=""><i class="${list.icon }"></i><span class="menu-text"> ${list.name } </span></a></li>
		</c:if>
	</c:if>
	
	<c:if test="${not empty list.children }">
		<li>
		<c:if test="${list.generatemenu eq 1 }"><!--  -->
			<a href="#" class="dropdown-toggle"><i class="${list.icon }"></i><span class="menu-text"> ${list.name } </span><b class="arrow icon-angle-down"></b></a>
		</c:if>
			<ul class="submenu">
				<c:forEach items="${list.children }" var="children">
					<c:if test="${children.generatemenu eq 1 }">
						<li class="home"><a href="javascript:void(0)" name="${children.url }" title="${children.name }" class="iframeurl"><i class="icon-double-angle-right"></i>${children.name }</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</li>
	</c:if>
	
</c:forEach>