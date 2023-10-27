
export class UserController {
    constructor() {
        $('#btn-login-now').click((event) => this.handleVerifyLogin(event));
        $('#btn-customer-create').click((event) => this.handleSaveCustomerValidation(event));

        this.guestRoleViewSet();
    }

    handleVerifyLogin(event) {
        event.preventDefault();
        // test for userName use as role no need pass to log
        // use customerRoleViewSet,admin,user as username
        const userName = $('#txtLoginUsername').val().toUpperCase();
        const password = $('#txtLoginPassword').val();

        // $.ajax({
        //     type: "GET",
        //     url: "",
        //     success: (response) => {
        // const user = JSON.parse(response);
        const userRole = userName//user.userRole
        if (userRole == 'CUSTOMER') {
            this.customerRoleViewSet();
        } else if (userRole == 'USER') {
            this.userRoleViewSet();
        } else if (userRole == 'ADMIN') {
            this.adminRoleViewSet();
        } else {
            alert('username or password wrong');
            return;
        }
        $('#txtLoginUsername').val('')
        $('#txtLoginPassword').val('')
        $('#isRememberMe').prop('checked', false);
        $('#btn-sign-out').show();
        $('#header').show();
        $('#nav-bar').show();
        $('#login-section').hide();
        $('#btn-open-login').hide();
        $('#dashboard-section').show();

        // $('#header_img').prop('src',user.profile);
        $('#header_img').show();
        //     },
        //     error: (error) => {

        //     }
        // })
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
            formData.append('userName', userName);
            formData.append('password', password);

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
            url: "",
            data: customer,
            contentType: false,
            processData: false,
            success: (data) => {
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
new UserController();