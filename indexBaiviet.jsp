<%@page import="java.util.ArrayList"%>
<%@page import="bean.BaiViet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/templates/admin/inc/left_bar.jsp" %>  
 <%@include file="/templates/admin/inc/header.jsp" %>  
<div class="templatemo-content-container">
	<div class="templatemo-content-widget white-bg">
		<div class="bottom-spacing">
			<!-- Button -->
			<%
		 	int msg = 0;
		 if(request.getParameter("msg") !=null){
			 msg =Integer.parseInt(request.getParameter("msg"));
		 switch(msg){
		 	case 0: out.print("<p style='background: yellow; color: red ; font-weight:bold'>Có lỗi trong quá trình thực hiện</p>"); break;
		 	case 1: out.print("<p style='background: yellow; color: red ; font-weight:bold'>Thêm thành công</p>");break;
		 	case 2: out.print("<p style='background: yellow; color: red ; font-weight:bold'> Sửa thành công</p>"); break;
		 	case 3: out.print("<p style='background: yellow; color: red ; font-weight:bold'> Xóa thành công</p>"); break;
		 }
		 }
		 %>
			<div class="float-left">
			
				<a href="<%=request.getContextPath() %>/admin/add-baiviet" title="Thêm bài viết" class="templatemo-white-button">Thêm bài viết</a>
				
			</div>
			<div class="clear">
			</div>
		</div>
		<h2 class="text-uppercase margin-bottom-10 text-center">Bài viết</h2>
		<br />
		<br />
		  		<form action="" method="POST">
			<div class="table-responsive">
				<table id="myTable" class="table-hover table-bordered table-striped" width ="100%">
					<thead>
						<tr class="text-center">
							<th>#</th>
							<th class="hidden">ID</th>
							<th>Tên</th>
							<th>Danh mục</th>
							<th>Trạng thái</th>
							<th>Hình ảnh</th>
							<th>Ngày đăng</th>
							<th>Chức năng</th>
							<th>
								<div class="margin-right-15 templatemo-inline-block">                      
								Chọn:
					              <input type="checkbox" name="chkAll" id="chkAll" value="" />
					              <label for="chkAll" class="font-weight-400"><span></span></label>
					              <%
								if((objU.getCapbac().equals("Ad"))){
					   				 %>
					              <input type="submit" name="dels" id="dels" value="Xóa" onclick="return valDels();" />
					              <%} %>
					            </div>
							</th>
						</tr>

					</thead>
					<tbody>
						<%
						ArrayList<BaiViet> listBv = (ArrayList<BaiViet>)request.getAttribute("listBaiViet");
						
						for(BaiViet bv : listBv)
						{
							
					    %>
												
						<tr>
							<td class="text-center"></td>
							<td class="id-news text-center hidden"><%=bv.getId_baiviet() %></td>
							<td><a href=""><%=bv.getTenbaiviet() %></a></td>
							<td><%=bv.getTendanhmuc() %></td>
							<td class="edit_active text-center"id="setactive-<%=bv.getId_baiviet()%>"><a
									href="javascript:void(0)"
									onclick="return setActive(<%=bv.getId_baiviet()%>)"
									title="Kích hoạt"> <%if(bv.isActive()){ %> <img
										src="<%=request.getContextPath() %>/templates/admin/images/tick-circle.gif"
										alt="" /> <%} else { %> <img
										src="<%=request.getContextPath() %>/templates/admin/images/minus-circle.gif">
										<%} %></a>

								
							</d>
							<td align="center">
								<img src="<%=request.getContextPath() %>/files/<%=bv.getHinhanh() %>" width="100px">
							</td>
							<td align="center">
								08-10-2016							</td>
							<td align="center">
							
						<%
								if((objU.getCapbac().equals("Mod"))||(objU.getCapbac().equals("Ad"))){
					    %>
								<a href="<%=request.getContextPath()%>/admin/edit-baiviet?nid=<%=bv.getId_baiviet()%>">
									Sửa
								</a>
								<%} %>
								
						<%
								if((objU.getCapbac().equals("Ad"))){
					    %>
								<a href="<%=request.getContextPath()%>/admin/del-baiviet?nid=<%=bv.getId_baiviet()%>" onclick="return confirmAction()">
									Xóa
								</a>
								<%} %>
							</td>
							<td class="text-center">
								<div class="margin-right-15 templatemo-inline-block">                      
					              <input type="checkbox" name="iddel[]" id ="<%=bv.getId_baiviet() %>" value="<%=bv.getId_baiviet() %>" />
					              <label for="<%=bv.getId_baiviet() %>" class="font-weight-400"><span></span></label>
					            </div>
							</td>
						</tr>
												
						
						<%} %>						
						
					</tbody>
				</table>
			</div>
		</form>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>
       
 <script type="text/javascript">
	function confirmAction() {
	return confirm("Bạn có chắc muốn xóa?")
	}
</script> 



<script type="text/javascript">
function valDels()
{
	
    var checkedAtLeastOne = false;
    $('input[type="checkbox"][name="iddel[]"]').each(function() {
        if ($(this).is(":checked")) {
            checkedAtLeastOne = true;
        }
    });
    
    if (checkedAtLeastOne == true){
		return confirm('Bạn thật sự muốn xóa Sách này?');
    } else {
    	alert('Vui lòng chọn ít nhất 1 tin để xóa');
    	return false;
    }
}

$('#chkAll').click(function(event) {
  if(this.checked) {
      // Iterate each checkbox
      $(':checkbox').each(function() {
          this.checked = true;
      });
  }
  else {
    $(':checkbox').each(function() {
          this.checked = false;
      });
  }
});

function setActive(id){
	$.ajax({
		url: '<%=request.getContextPath()%>/admin/ajax',
		type: 'POST',
		cache: false,
		data: {aid : id, 
			  },
		success: function(data){
			$('#setactive-' + id).html(data);
		},
		error: function (){
			alert('Có lỗi xảy ra');
		}
	});
	return false;
}

</script>
<%@include file="/templates/admin/inc/footer.jsp" %>             
        </div>  
      </div>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->

    <script type="text/javascript" src="<%=request.getContextPath() %>/js/templatemo-script.js"></script>      <!-- Templatemo Script -->    

	<script>
      $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();        
      });
    </script>	
  </body>
</html>