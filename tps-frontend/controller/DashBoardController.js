export class DashBoardController {
    constructor() {
        $('#btn-dashboard').click((event) => {
            event.preventDefault();
            this.handleLoadDashBoard();
        });
        $('#see-more-prackage').click((event) => {
            event.preventDefault();
            document.getElementById("btn-package").click();
        });

        this.handleLoadDashBoard();
    }
    handleLoadDashBoard() {
        this.loadAllPackage();
        this.loadAllHotel();
        this.loadAllVehicle();
    }

    loadAllPackage() {
        $('#dash-package-body .row').empty();
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/package/api/v1/package/public",
            success: (response) => {
                response?.sort((a, b) => a.price - b.price);
                response = response?.slice(0, 12);
                $('#dash-package-body .row').append(response?.map(this.renderPackageCard).join(''));
            }
            ,
            error: (error) => {
                $('#see-more-prackage').hide();
                console.log(error)
            }
        })

        // const obj = {
        //     category:"ex 13 category",
        //     area:"ex area",
        //     price: 500.00,
        //     averageNoOfDate: 1,
        // }
        // $.ajax({
        //     type: "POST",
        //     url: "http://localhost:8090/package/api/v1/package/public",
        //     data: JSON.stringify(obj),
        //     contentType: "application/json",
        //     success: (data) => {
        //         console.log(data)
        //     },
        //     error: (error) => {
        //         console.log(error)
        //     }
        // });

    }
    renderPackageCard(data, index) {
        $('#see-more-prackage').show();
        return `
            <div class="package-card col-lg-3 col-md-5 col-sm-10">
                <div class="package-category">${data.category}</div>
                <div class="package-area">${data.area}</div>
                <div class="package-date">No of day : ${data.averageNoOfDays}</div>
                <div class="package-price">Price : LKR ${data.price}</div>
                <input type="hidden" class="package-id" value="${data.id}">
            </div>
        `;
    }

    loadAllHotel() {

    }
    loadAllVehicle() {

    }
}
new DashBoardController();