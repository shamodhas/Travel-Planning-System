export class HotelController {
    constructor() {
        $('#btn-hotel').click((event) => {
            event.preventDefault();
            this.handleLoadAllHotel();
        });
       
    }
    
}

new HotelController();