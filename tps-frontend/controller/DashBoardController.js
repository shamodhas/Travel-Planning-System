export class DashBoardController {
    constructor() {
        $('#btn-dashboard').click((event) => {
            event.preventDefault();
            this.handleLoadDashBoard();
        });

        this.handleLoadDashBoard();
    }
    handleLoadDashBoard() {
        this.loadAllPackage();
        this.loadAllHotel();
        this.loadAllVehicle();
    }

    loadAllPackage() {
        // $('#dash-package-body').
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/package/api/v1/package/public",
            success: (response) => {
                console.log(response)
                $('#dash-package-body .row').append(
                    '<div class="row">' +
                    response?.map(
                        (data) => {
                            '<div class="package-card col-md-3">' +
                                '<div class="package-category">Regular</div>' +
                                '<div class="package-area">anuradhapura</div>' +
                                '<div class="package-date">1 day</div>' +
                                '<div class="package-price">500.00</div>' +
                                '</div>'
                        }
                    )
                    + '</div>'
                );

            }
            ,

            error: (error) => {
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
    loadAllHotel() {

    }
    loadAllVehicle() {

    }
}
new DashBoardController();