const val EPS = 0.0000001

class Complex(val real: Double, val imaginary: Double) {

    operator fun plus(other: Complex): Complex {
        return Complex(this.real + other.real, this.imaginary + other.imaginary)
    }


    operator fun minus(other: Complex): Complex{
        return Complex(this.real - other.real, this.imaginary - other.imaginary)
    }


    operator fun times(other: Complex): Complex {
        val realPart = this.real * other.real - this.imaginary * other.imaginary
        val imaginaryPart = this.real * other.imaginary + this.imaginary * other.real
        return Complex(realPart, imaginaryPart)
    }


    val abs: Double
        get() = kotlin.math.sqrt(this.real * this.real + this.imaginary * this.imaginary)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Complex) return false
        return kotlin.math.abs(this.real - other.real) < EPS &&
                kotlin.math.abs(this.imaginary - other.imaginary) < EPS
    }

    override fun hashCode(): Int {
        return 31 * real.hashCode() + imaginary.hashCode()
    }

    override fun toString(): String {
        return "$real + ${imaginary}i"
    }
}
