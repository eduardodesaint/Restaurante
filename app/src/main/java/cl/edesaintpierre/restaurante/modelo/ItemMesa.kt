package cl.edesaintpierre.restaurante.modelo

class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int) {

    fun calcularSubtotal(): Int = itemMenu.precio.toInt() * cantidad
}