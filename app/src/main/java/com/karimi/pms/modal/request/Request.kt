package com.karimi.pms.modal.request

class Request {
     var title: String
     var address: String
     var date: String
     var dayName: String
//     var title : String? = null
//     var address : String? = null
//     var date : String? = null
//     var dayName : String? = null

     constructor(title: String, address: String, date: String, dayName: String) {
          this.title = title
          this.address = address
          this.date = date
          this.dayName = dayName

     }
}