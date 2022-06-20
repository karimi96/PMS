package com.karimi.pms.modal

class Request {
     var title : String
     var address : String
     var date : String
     var dayName : String

    constructor(title: String, address: String, date: String, dayName: String) {
        this.title = title
        this.address = address
        this.date = date
        this.dayName = dayName
    }
}