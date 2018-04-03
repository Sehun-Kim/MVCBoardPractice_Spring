<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- user의 로그인 상태를 알려주는 jsp 파일 단독이 아닌 include되어 사용된다. -->

<div class="container">
		<h1>${pageContext.request.userPrincipal.name} 님</h1>
		<a href="${pageContext.request.contextPath}/j_spring_security_logout" style="color: red">
			${pageContext.request.userPrincipal.name} 로그아웃</a>
</div>