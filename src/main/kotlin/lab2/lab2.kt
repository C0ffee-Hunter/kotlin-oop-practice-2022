package lab2

import kotlin.math.sqrt

fun main()
{
    val color1 = ColorOfRGBA(1, 1, 1, 1.0)
    val color2 = ColorOfRGBA(3, 4, 56, 0.23)
    val circle1: Circle = Circle(2, color1, color2)
    circle1.calcArea()
    circle1.poop()
    val square1: Square = Square(12.0,15.0, color2, color1)
    square1.calcArea()
    square1.poop()
    val triangle1: Triangle = Triangle(color2, color1)
    triangle1.calcArea()
    triangle1.poop()

    val figurelist = ShapeCollector()
    figurelist.addFigures(circle1)
    figurelist.addFigures(square1)
    figurelist.addFigures(triangle1)

    figurelist.minSquare()
    figurelist.getBorderFigure()
    figurelist.getFillFigure()

}

data class ColorOfRGBA(val red: Int, val green: Int, val blue: Int, val alpha: Double){
    override fun toString(): String
    {
        return "Color figure of RGBA: $red, $green, $blue, $alpha"
    }
}

interface Shape2d
{
    fun calcArea(): Double
}

interface ColoredShape2d : Shape2d
{
    val borderColor: ColorOfRGBA
    val fillColor: ColorOfRGBA
}

class Circle(radius_1: Int, borderColor_1: ColorOfRGBA, fillColor_1: ColorOfRGBA): ColoredShape2d
{
    var radius: Int = radius_1
    val pi: Double = 3.14
    override val borderColor: ColorOfRGBA = borderColor_1
    override val fillColor: ColorOfRGBA = fillColor_1
    override fun calcArea(): Double
    {
        val square: Double = pi * radius * radius
        println("Square: $square")
        return square
    }
    fun poop()
    {
        println("Border Color: $borderColor")
        println("Fill Color: $fillColor")
    }
}
class Square(firstSide_1: Double, secondSide_1: Double, borderColor_1: ColorOfRGBA, fillColor_1: ColorOfRGBA): ColoredShape2d
{
    val firstSide = firstSide_1
    val secondSide = secondSide_1
    override val borderColor: ColorOfRGBA = borderColor_1
    override val fillColor: ColorOfRGBA = fillColor_1
    override fun calcArea(): Double
    {
        val square: Double = firstSide * secondSide
        println("Square: $square")
        return square
    }
    fun poop()
    {
        println("Border Color: $borderColor")
        println("Fill Color: $fillColor")
    }
}
class Triangle(borderColorTriangle: ColorOfRGBA, fillColorTriangle: ColorOfRGBA): ColoredShape2d
{
    val triangleFirstSide: Double = 3.0
    val triangleSecondSide: Double = 4.0
    val triangleThirdSide: Double = 5.0
    override val borderColor: ColorOfRGBA = borderColorTriangle
    override val fillColor: ColorOfRGBA = fillColorTriangle
    override fun calcArea(): Double
    {
        val square: Double
        if (triangleFirstSide > 0.0 && triangleSecondSide > 0.0 && triangleThirdSide > 0.0) {
            if (triangleFirstSide + triangleSecondSide > triangleThirdSide
                && triangleFirstSide + triangleThirdSide > triangleSecondSide
                && triangleSecondSide + triangleThirdSide > triangleFirstSide
            ) {
                val perimeter = (triangleFirstSide + triangleSecondSide + triangleThirdSide) / 2
                square = sqrt(
                    perimeter * (perimeter - triangleFirstSide) * (perimeter - triangleSecondSide) * (perimeter - triangleThirdSide)
                )
            } else error(("Invalid triangle sides"))
            println("Square: $square")
        } else error(("One of triangle sides is  0"))
        return square
    }
    fun poop()
    {
        println("Border Color: $borderColor")
        println("Fill Color: $fillColor")
    }
}

class ShapeCollector()
{
    var figureList: ArrayList<ColoredShape2d> = arrayListOf()
    fun addFigures(figure: ColoredShape2d)
    {
        figureList.add(figure)
    }

    fun minSquare(): ColoredShape2d
    {
        val size = figureList.size - 1
        var minSquare = figureList[0].calcArea()
        var minFigure: ColoredShape2d = figureList[0]
        for (i in 0..size)
        {
            if (figureList[i].calcArea() <= minSquare)
            {
                minSquare = figureList[i].calcArea()
                minFigure = figureList[i]
            }
        }
        println ("min: $minSquare")
        return minFigure
    }

    fun maxSquare(): ColoredShape2d
    {
        val size = figureList.size - 1
        var maxSquare = figureList[0].calcArea()
        var maxFigure: ColoredShape2d = figureList[0]
        for (i in 0..size)
        {
            if (figureList[i].calcArea() >= maxSquare)
            {
                maxSquare = figureList[i].calcArea()
                maxFigure = figureList[i]
            }
        }
        println ("min: $maxSquare")
        return maxFigure
    }

    var sumSquare: Double = 0.0

    fun sumSquareFigures()
    {
        sumSquare += figureList[(figureList.size) - 1].calcArea()
        println(sumSquare)
    }

    fun getBorderFigure(): Map<ColorOfRGBA, List<ColoredShape2d>> {
        return figureList.groupBy { it.fillColor }
    }

    fun getFillFigure(): Map<ColorOfRGBA, List<ColoredShape2d>> {
        return figureList.groupBy { it.borderColor }
    }
}
