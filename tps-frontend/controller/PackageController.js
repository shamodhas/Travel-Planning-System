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
    }
    handleLoadAllPackage() {
        console.log("awa")
        $('#package-body .row').empty();
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/package/api/v1/package/public",
            success: (response) => {
                response?.sort((a, b) => a.price - b.price);
                $('#package-body .row').append(response?.map(this.renderPackageCard).join(''));
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })

    }
    renderPackageCard(data, index) {
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

        // const package = 
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
            url: "http://localhost:8090/package/api/v1/package/public",
            data: JSON.stringify(aPackage),
            contentType: "application/json",
            success: (data) => {
                console.log(data)
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
    }
}

new PackageController();