package id.kardihaekal.e_commerce_design.model

class ProductModel(
    var name: String,
    var store: String,
    var price: Long,
    var price_old: Long,
    var img: String,
    var rating: Float,
    var discount: Int
)