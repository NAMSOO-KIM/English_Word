<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="header.jsp" />
 <script type="text/javascript">
	
	function fn_idOverlap() {
		$.ajax({
			url : "/myword/idOverlap",
			type : "post",
			dataType : "json",
			data : { "user_id" : $("#user_id").val()},
			success : function(data) {
				if(data == 1) {
					alert("중복된 아이디 입니다.");
					$("#submit").attr("disabled", "disabled");
				} else if(data == 0) {
					alert("사용가능한 아이디 입니다.");
					$("#submit").removeAttr("disabled");
				}
			}
		})
	}

 </script>
</head>

<body>
	<!-- 여기부터 시작 -->
	<div class="container">
    <div class="row">
    <div class="col-sm-12 text-center" >
    <div class="col-sm-3"></div>
     
    <div class="col-sm-6">
    <h2>회원가입</h2>
    <form action="registerAction" method="post" onsubmit="return validate()">
        <table class="table table-boardered">
            <tr>
                <th>아이디</th>
                <td><input type="text" class="form-control" id = "user_id" name="user_id"></td>
                <td><input type="button" name="check_dupul" value="중복 확인" onclick="fn_idOverlap();"></button>
            </tr>
            <tr>
                <th>패스워드</th>
                <td><input type="password" class="form-control" name="password" placeholder="비밀번호는 영문만 넣어주세요"></td>      
            </tr>
             
            <tr>
                <th>패스워드확인</th>
                <td><input type="password" class="form-control" name="pass2"></td>        
            </tr>
             
            <tr>
                <th>이름</th>
                <td><input type="text" class="form-control" name="name"></td>       
            </tr>
                          
            <tr>
                <td colspan="2">
                <input type="submit" id="subit" class="btn btn-primary" value="전송">
                <input type="reset" class="btn btn-danger" value="취소">
                </td>
            </tr>
             
             
        </table>
    </form>
    </div>
     <div class="modal-body" id="checkMessage">
	</div>
    </div>
    </div>
</div>

</body>
</html>