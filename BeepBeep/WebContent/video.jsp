<%@page import="controller.youtube"%>
<%@page import="java.util.Random"%>
<%@page import="model.memberDTO"%>
<%@page import="model.youtubeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.youtubeDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Generic - Phantom by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<style>

@font-face {
    font-family: 'twayair';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_tway@1.0/twayair.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body {
	font-family:'twayair';
	font-size:30px;
}

input {
	font-family:'ROKABold';
	font-size:30px;
	color : white;
}

.ass {
	list-style-type: none;
	margin: 0;
	padding: 0;
	background-color: white;

.ass:after {
	content: '';
	display: block;
	clear: both;
}

li {
	float: left;
}

li a {
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not(.active) {
	background-color: #111;
}

.active:hover {
	background-color: #4CAF50;
}
</style>
</head>
<body>
	

<%
	memberDTO dto = (memberDTO)session.getAttribute("info");
	//System.out.print("카테고리 :" +dto.getCategory());
	//System.out.println("성별 :" + dto.getGender());
	
	youtubeDAO dao = new youtubeDAO();

	//ArrayList<youtubeDTO> al =  dao.select(dto.getGender());
	
	//System.out.println("유튜버 :" + al.get(1).getYoutuber() 
			//+ "성별 : " +al.get(1).getGender());
%>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header">
			<div class="inner">

				<!-- Logo -->
				<a href="main.jsp" class="logo"> <span class="symbol"><img
						src="images/hehe.png" alt="" /></span><span class="title">Web & PT</span>
				</a>

				<!-- Nav -->
				<nav>
					<ul>
						<li><a href="#menu">Menu</a></li>
					</ul>
				</nav>

			</div>
		</header>

		<!-- Menu -->
		<nav id="menu">
			<h2>Menu</h2>
			<ul>

				<li><a href="main.jsp">메인화면으로</a></li>
				<li><a href="video.jsp">영상</a></li>
				<li><a href="recommend.jsp">식단추천</a></li>
				<li><a href="meal.jsp">식단</a></li>
				<li><a href="caltest.html">캘린더</a></li>
			</ul>
		</nav>

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<h1>부 위 별ㅤ영 상ㅤ추 천</h1>
				<ul class = "ass">
				
		<% if(dto.getGender().equals("1")){ %>
			<form action="video.jsp">
				<input type="submit" name="part" value="back"><span>ㅤ</span>
				<input type="submit" name="part" value="shoulder"><span>ㅤ</span>
				<input type="submit" name="part" value="legs"><span>ㅤ</span>
				<input type="submit" name="part" value="abs"><span>ㅤ</span>
				<input type="submit" name="part" value="chest">
			</form>
		<%} else{%>
			<form action="video.jsp">
				<input type="submit" name="part" value="shoulder"><span>ㅤ</span>
				<input type="submit" name="part" value="abs"><span>ㅤ</span>
				<input type="submit" name="part" value="arm"><span>ㅤ</span>
				<input type="submit" name="part" value="hip"><span>ㅤ</span>
				<input type="submit" name="part" value="stretchingr">
				</form>
		<%} %>
	</ul>
			
				
				<%
				youtube Youtube = new youtube(request, session);
				ArrayList<youtubeDTO> al = Youtube.getAl();
				ArrayList<youtubeDTO> al2 =  dao.select(dto.getGender(), "abs");
				System.out.println(al.size());
				System.out.println(al2.size());
				
		
				//if (al.get(0).getPart()==null){
				if (al.size()==0){
					 for (int i = 0; i<5; i++){
						 Random ran = new Random();
				out.print("<iframe width='1344' height='700' src = ");
				out.print("'https://www.youtube.com/embed/");
                out.print(al2.get(i).getLink().split("=")[1]);
                out.print("'");
				out.print("frameborder='0' ");
				out.print("allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' ");
				out.print("allowfullscreen></iframe>");
		
					 }
					}else{
				
				for(int j = 0; j <5; j++){
				Random ran = new Random();
				out.print("<iframe width='1344' height='700' src = ");
				out.print("'https://www.youtube.com/embed/");
                out.print(al.get(j).getLink().split("=")[1]);
                out.print("'");
				out.print("frameborder='0' ");
				out.print("allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' ");
				out.print("allowfullscreen></iframe>");
				}
					}
				%>

			</div>
	</div>


	
	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>


</body>
</html>