export class PackageController {
    constructor() {
        $('#btn-package').click((event) => {
            event.preventDefault();
            this.handleLoadAllPackage();
        });
        $('#btnSavePackage').click((event) => {
            event.preventDefault();
            this.handleValidatePackage()
        });
        $('#btnUpdatePackage').click((event) => {
            event.preventDefault();
            this.handleUpdatePackage()
        });
        $('#btnDeletePackage').click((event) => {
            event.preventDefault();
            this.handleDeletePackage()
        });
        $('#btnResetPackage').click((event) => {
            event.preventDefault();
            this.reset();
        });
        $("#package-body .row").on("click", ".package-card", (event) => {
            event.preventDefault();
            this.handleSelectPackage(event);
        });
    }
    handleSelectPackage(event) {
        const packageId = $(event.currentTarget).find('.package-id').val();
        const category = $(event.currentTarget).find('.package-category').text();
        const area = $(event.currentTarget).find('.package-area').text();
        const price = $(event.currentTarget).find('.package-price').text().slice(12,);
        const averageNoOfDays = $(event.currentTarget).find('.package-date').text().slice(12,);

        $('#txtPackageId').val(packageId);
        $('#txtPackageCategory').val(category);
        $('#txtPackageArea').val(area);
        $('#txtPackagePrice').val(price);
        $('#txtPackageDays').val(averageNoOfDays);

        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });

        console.log({
            packageId,
            category,
            area,
            price,
            averageNoOfDays
        });
    }
    handleLoadAllPackage() {
        $('#userRole').val('ADMIN');// temp
        
        let userRole = $('#userRole').val();
        $('#package-body .row').empty();
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/package/api/v1/package/public",
            success: (response) => {
                response?.sort((a, b) => a.price - b.price);
                $('#package-body .row').append(response?.map(this.renderPackageCard).join(''));
                $('.package-card').attr("package-card-hover-text",
                    (userRole === 'CUSTOMER') ? "Book now" :
                        (userRole === ('ADMIN' || 'USER')) ? "Edit Now" :
                            "Login Now");
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })

    }
    renderPackageCard(data) {
        return `
            <div class="package-card col-lg-3 col-md-5 col-sm-10">
                <div class="package-category">${data.category}</div>
                <div class="package-area">${data.area}</div>
                <div class="package-date">No of day : ${data.averageNoOfDays}</div>
                <div class="package-price">Price : LKR ${data.price}</div>
                <input type="hidden" class="package-id" value="${data.packageId}">
            </div>
        `;
    }
    handleValidatePackage() {
        const category = $('#txtPackageCategory').val();
        const area = $('#txtPackageArea').val();
        const price = $('#txtPackagePrice').val();
        const averageNoOfDays = $('#txtPackageDays').val();

        // validation

        this.handleSavePackage({
            category,
            area,
            price,
            averageNoOfDays
        });
    }
    handleSavePackage(aPackage) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/package/api/v1/package",
            data: JSON.stringify(aPackage),
            contentType: "application/json",
            success: (data) => {
                this.handleLoadAllPackage()
                this.reset();
            },
            error: (error) => {
                console.log(error)
            }
        });
    }
    reset() {
        $('#txtPackageCategory').val('');
        $('#txtPackageArea').val('');
        $('#txtPackagePrice').val('');
        $('#txtPackageDays').val('');
        $('#txtPackageId').val('');
    }
    handleUpdatePackage() {
        const packageId = $('#txtPackageId').val();
        if (packageId) {
            const category = $('#txtPackageCategory').val();
            const area = $('#txtPackageArea').val();
            const price = $('#txtPackagePrice').val();
            const averageNoOfDays = $('#txtPackageDays').val();

            // validation 

            const aPackage = {
                category,
                area,
                price,
                averageNoOfDays
            }

            $.ajax({
                type: "PUT",
                url: "http://localhost:8090/package/api/v1/package/" + packageId,
                data: JSON.stringify(aPackage),
                contentType: "application/json",
                success: (response) => {
                    this.handleLoadAllPackage()
                    this.reset();
                    alert(response);
                }
                ,
                error: (error) => {
                    console.log(error)
                }
            });
        } else {
            alert('need package select before update ');
        }
    }
    handleDeletePackage() {
        const packageId = $('#txtPackageId').val();
        if (packageId) {
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8090/package/api/v1/package/" + packageId,
                success: (response) => {
                    this.handleLoadAllPackage()
                    this.reset();
                    alert(response);
                }
                ,
                error: (error) => {
                    console.log(error)
                }
            });
        } else {
            alert('need package select before delete ');
        }
    }
}

new PackageController();