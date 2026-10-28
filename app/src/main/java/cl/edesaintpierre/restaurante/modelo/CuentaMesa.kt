package cl.edesaintpierre.restaurante.modelo

class CuentaMesa(val mesa: Int) {

    private val _items = mutableListOf<ItemMesa>()
    var aceptaPropina: Boolean = true

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        _items.add(ItemMesa(itemMenu, cantidad))
    }

    fun agregarItem(itemMesa: ItemMesa) {
        _items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int =
        _items.sumOf { it.calcularSubtotal() }

    fun calcularPropina(): Int =
        if (aceptaPropina) calcularTotalSinPropina() / 10 else 0

    fun calcularTotalConPropina(): Int =
        calcularTotalSinPropina() + calcularPropina()
}