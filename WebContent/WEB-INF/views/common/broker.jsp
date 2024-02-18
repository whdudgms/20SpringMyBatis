<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- 중복의제거! 다른 jsp include를 하려공!  -->
<script type="text/javascript">
	//컨트롤러에서 보낸 메세지가 있을 경우
	window.onload = function(e){ 
		var resultMsg ;
		 resultMsg = '${resultMsg}';
		var resultCode = '${resultCode}';
		
		if(resultMsg.length > 0){
		window.alert(resultMsg);
		}else{
			window.alert('메세지없음.');
		}
		if(resultCode == 'ok'){
			window.location.href = '<c:url value="{nextUri}"/>';
		}
		window.location.href = '<c:url value="{nextUri}"/>';
	}
</script>
<body>

	

</body>
</html>