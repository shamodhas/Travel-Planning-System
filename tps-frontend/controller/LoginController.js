
export class LoginController {
    constructor() {
        $('#btn-login-now').click((event) => this.handleVerifyLogin(event));
        $('#btn-customer-create').click((event) => this.handleSaveCustomerValidation(event));

        this.guestRoleViewSet();
    }

    handleVerifyLogin(event) {
        event.preventDefault();
        const username = $('#txtLoginUsername').val();
        const password = $('#txtLoginPassword').val();
        console.log(username)
        console.log(password)
        const user = {
            username,
            password
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8090/auth/api/v1/auth/token",
            data: JSON.stringify(user),
            contentType: "application/json",
            success: (response) => {
                console.log(response)
                localStorage.setItem('token', response.token);
                // const userDetails = this.getUSerDetails(username,response);
                // const user = JSON.parse(response);
                // const userRole = userName//user.userRole
                if (response.userRole == 'CUSTOMER') {
                    this.customerRoleViewSet();
                } else if (response.userRole == 'USER') {
                    this.userRoleViewSet();
                } else if (response.userRole == 'ADMIN') {
                    this.adminRoleViewSet();
                } else {
                    alert('username or password wrong');
                    return;
                }
                $('#userId').val(response.userId);
                $('#userRole').val(response.userRole);
                $('#txtLoginUsername').val('')
                $('#txtLoginPassword').val('')
                $('#isRememberMe').prop('checked', false);
                $('#btn-sign-out').show();
                $('#header').show();
                $('#nav-bar').show();
                $('#login-section').hide();
                $('#btn-open-login').hide();
                $('#dashboard-section').show();

                $('#header_img').prop('src',"data:image/jpeg;base64, "+user.profile);
                $('#header_img').show();
            },
            error: (error) => {
                alert('username or password wrong')
            }
        })
    }
    getUSerDetails(username,token) {
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/auth/api/v1/auth/" + username,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            success: (user) => {
                console.log(user)
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    handleSaveCustomerValidation(event) {
        event.preventDefault();

        const name = $('#txtCustomerName').val();
        const email = $('#txtCustomerEmail').val();
        const nic = $('#txtCustomerNic').val();
        const address = $('#txtCustomerAddress').val();
        const profileImg = document.getElementById('cropped-image');
        const userName = $('#txtCustomerUserName').val();
        const password = $('#txtCustomerPassword').val();
        const rePassword = $('#txtCustomerRePassword').val();

        try {
            var arr = profileImg.src.split(',');
            var mime = arr[0].match(/:(.*?);/)[1];
            var bstr = atob(arr[1]);
            var n = bstr.length;
            var u8arr = new Uint8Array(n);

            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }

            const profile = new File([u8arr], 'image.jpg', { type: mime });
            // validation

            var formData = new FormData();

            formData.append('name', name);
            formData.append('email', email);
            formData.append('nic', nic);
            formData.append('address', address);
            formData.append('profile', profile);
            formData.append('username', userName);
            formData.append('password', password);
            formData.append('userRole', "CUSTOMER");

            this.handleSaveCustomer(formData);
        } catch (e) {
            console.log(e)
            alert('please select profile picture');
            return;
        }
    }
    handleSaveCustomer(customer) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/auth/api/v1/auth/register",
            data: customer,
            contentType: false,
            processData: false,
            success: (data) => {
                document.getElementById("btn-go-login").click();
                alert('success')
            },
            error: (error) => {
                alert(error)
            }
        });
    }

    guestRoleViewSet() {
        $('#btn-booking').hide();
        $('#btn-customer').hide();
        $('#btn-user').hide();
    }

    customerRoleViewSet() {
        $('#btn-customer').hide();
        $('#btn-user').hide();
        $('#btn-booking').show();
    }

    userRoleViewSet() {
        $('#btn-user').hide();
        $('#btn-booking').show();
        $('#btn-customer').show();
    }

    adminRoleViewSet() {
        $('#btn-booking').show();
        $('#btn-customer').show();
        $('#btn-user').show();
    }

    
}
new LoginController();