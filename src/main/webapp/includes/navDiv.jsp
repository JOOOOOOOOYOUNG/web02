<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctxPath" value="<%=request.getContextPath() %>" />   


	

	<nav class="navbar navbar-expand-lg bg-body-tertiary navbar-dark bg-dark mb-3" data-bs-theme="dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Blog</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse row" id="navbarScroll">
	      <ul class="col-lg-6 navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">회원정보</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link ">게시판</a>
	        </li>
     	    <li class="nav-item">
	         	<div class="text-white p-2">
	              <c:if test="${loginCheck!=null }">
	           	 	<a href="${ctxPath}/logout" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">로그아웃</a>
	              </c:if>
	    	      <c:if test="${loginCheck==null }">
	           	 	<a href="${ctxPath}/login" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">로그인</a>
	              </c:if>	      
	      	  	</div>
	        </li>
	      </ul>
	      
	      <div class="col-lg-6 d-flex  justify-content-around">
		      <form class="d-lg-flex d-none d-lg-block " role="search">
		        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Search</button>
		      </form>
		      <div class="text-white p-2">
	              <c:if test="${loginCheck!=null }">
	           	 	<label>${loginCheck.id}님</label>
	              </c:if>
	      	  </div>
	      </div>
	    
	    </div>
	  </div>
	</nav>


