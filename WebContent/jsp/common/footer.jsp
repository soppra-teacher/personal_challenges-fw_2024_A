<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="contents">



	<hr />
	<table class="layout-table w-100">
		<tr>
			<td class="w-25">
				<a href = "#" id = "backMenu" class = "btn-green btn-size-m" tabindex = "3" >
					�����E�ꗗ��ʂ֖߂�
				</a>
			</td>
			<td class="w-25">
				<a href = "#" id = logOut class = "btn-green btn-size-m" tabindex = "3" >
					���O�A�E�g
				</a>
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
		//���j���[��ʂ̉�ʑJ�ڏ���
	    document.getElementById('backMenu').addEventListener('click', function (event) {
	        var msg = "�ʉ�ʂɑJ�ڂ��܂�����낵���ł����H";
	        if (!confirm(msg)) {
	        	return;
	        } else {
	        	window.location.href = "./KankouListInit.do?operation=reserch"
	        }
	    });
		//���O�A�E�g�̉�ʑJ�ڏ���
	    document.getElementById('logOut').addEventListener('click', function (event) {
	        var msg = "�ʉ�ʂɑJ�ڂ��܂�����낵���ł����H";
	        if (!confirm(msg)) {
	        	return;
	        } else {
	        	window.location.href = "./Logout.do"
	        }
	    });
	</script>

	
</div>

