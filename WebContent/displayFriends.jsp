<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FAF | Facebook Annoying Friends</title>
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="site-header"><img src="img/faf-logo.png">
  <p>Facebook Annoying Friends</p>
</div>

<!-- ADDED INDIVIDUAL USER DATA -->
<div id="about-me">
  <div class="profile-holder"><img src="img/profile.png"></div>
  <div class="my-wrap">
    <p class="my-name">Work in progress</p>
    <div class="my-count">
      <p>Friends: <span>123</span></p>
      <p>Likes: <span>$123</span></p>
      <p>Wall Posts: <span>13</span></p>
      <p>Photos: <span>1241</span></p>
      <p>Games: <span>3434</span></p>
      <p>Checkins: <span>34367</span></p>
      <p>Notes: <span>57387</span></p>
    </div>
    <div class="my-total">
      <p>9876543</p>
    </div>
  </div>
</div>
<!-- END INDIVIDUAL DATA HTML -->


<div id="app-desc">
    <p class="app-desc-text">This app calculates a score for your Facebook friends comprised of their activity on Facebook. It should be noted that some 
    factors - such as privacy settings - can affect the score.</p>
</div>

<!--BEGIN FRIEND LOOP-->
<c:forEach items="${fl}" var="friend" varStatus="count">
  <c:choose> 
    <c:when test="${count.count == 1}"><c:set var="clss" value="red-tag" scope="session"/></c:when>
    <c:when test="${count.count == 2}"><c:set var="clss" value="yellow-tag" scope="session"/></c:when>
    <c:otherwise><c:set var="clss" value="green-tag" scope="session"/></c:otherwise>
  </c:choose>
  <div class="person-wrap">
    <div class="profile-holder"><img src="${friend.picSquare}"></div>
    <div class="data-wrap">
      <p class="data-name">${friend.name}</p>
      <div class="data-count">
        <p>Friends: <span>${friend.friendCount}</span></p>
        <p>Likes: <span>${friend.likesCount}</span></p>
        <p>Wall Posts: <span>${friend.wallCount}</span></p>
        <p>Photos: <span>${friend.photoCount}</span></p>
        <p>Games: <span>${friend.gameCount}</span></p>
        <p>Checkins: <span>${friend.checkinCount}</span></p>
        <p>Notes: <span>${friend.notesCount}</span></p>
      </div>
      <div class="count-total">
        <p>${friend.score}</p>
      </div>
      <div class="data-rank ${clss}">
        <p>#${count.count }</p>
      </div>
    </div>
    <div class="clear-both"></div>
  </div>
</c:forEach>
<!--END FRIEND LOOP DATA-->
</body>
</html>