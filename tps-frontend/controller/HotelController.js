export class HotelController {
    constructor() {
        $('#btn-hotel').click((event) => {
            event.preventDefault();
            this.handleLoadAllHotel();
        });
        $('.hotel-more-about').click((event)=>{
            event.preventDefault();
            this.handleViewHotelDetails();
        })
    }
    handleLoadAllHotel(){
            
    }
    handleViewHotelDetails(){

    }
}

new HotelController();