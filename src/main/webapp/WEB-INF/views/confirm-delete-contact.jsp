<%@  include file="./header.jspf"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>Confirm Deletion</h3>

<c:if test="${contact==null }">
	<h5>There is no record in the phonebook</h5>
</c:if>

<c:if test="${contact!= null }">

	<div class="row">
		<div class="col-md-5">
			<img class="img-thumbnail"
				src="${contact.avatar = ' '?'./assets/Images/download.png'  : contact.avatar}">
		</div>
		<div class="col-md-7">

			<table class="table">

				<tr>
					<td>FirstName</td>
					<td>${contact.firstname}</td>
				</tr>
				<tr>
					<td>LastName</td>
					<td>${contact.lastname}</td>
				</tr>
				<tr>
					<td>Email Address</td>
					<td>${contact.email}</td>
				</tr>
				<tr>
					<td>Phone number</td>
					<td>${contact.phone}</td>
				</tr>
				<tr>
					<td>Street Address</td>
					<td>${contact.address}</td>
				</tr>
				<tr>
					<td>City</td>
					<td>${contact.city}</td>
				</tr>
				<tr>
					<td>State/Provinence</td>
					<td>${contact.state}</td>
				</tr>
				<tr>
					<td>Pincode</td>
					<td>${contact.pincode}</td>
				</tr>
				<tr>
					<td>Country</td>
					<td>${contact.country}</td>
				</tr>


			</table>

			<p>This is going to be permanently deleted</p>
			<p>if you are sure enter PERMANENTLY DELETE in the text box given
				below</p>
			<form method="POST">
				<input type="hidden" name="id" value="${contact.id}">
				<div class="form-group row">
					<input type="text" class="col-md-4" name="confirm">
					<button class="btn btn-danger col-md-3">Delete</button>

				</div>
				<h5 class = "text-danger" >${errMessage}</h5>
			</form>

		</div>

	</div>

</c:if>

<hr>
<%@  include file="./footer.jspf"%>
