package main.kotlin.lab2

import kotlin.math.sqrt

fun main() {
    val color1 = ColorOfRGBA(2, 123, 56, 0.31)
    val color2 = ColorOfRGBA(3, 4, 56, 0.23)
    val color3 = ColorOfRGBA(45, 234, 12, 1.0)
    val color4 = ColorOfRGBA(5, 103, 78, 0.78)
    val circle1 = Circle(2, color1, color2)
    val square1 = Square(12.0, 15.0, color3, color1)
    val square2 = Square(6.0, 13.0, color4, color2)
    val triangle1 = Triangle(6.0, 13.0, 23.0, color4, color2)
    val figurelist = ShapeCollector()

    figurelist.addFigures(circle1)
    figurelist.addFigures(square1)
    figurelist.addFigures(square2)
    figurelist.addFigures(triangle1)

    //sum of all figure
    figurelist.sumSquareFigures()
    //list of all stored figure
    figurelist.listAllFigures()
    //ашпгку list size
    figurelist.sizeFigureList()
    //Returning a figure with a min area
    figurelist.minSquare()
    //Returning a figure with a max area
    figurelist.maxSquare()
    //return shapes grouped by fill color
    figurelist.mapFillColor()
    //return shapes grouped by border color
    figurelist.mapBorderColor()
    //find all shapes by border color
    figurelist.searchBorderColor(color3)
    //find all shapes by fill color
    figurelist.searchFillColor(color2)
    //finding shapes by a specific type
    figurelist.searchType("Triangle")

}

data class ColorOfRGBA(val red: Int, val green: Int, val blue: Int, val alpha: Double) {
    override fun toString(): String {
        return "Color figure of RGBA: $red, $green, $blue, $alpha"
    }
}

interface Shape2d {
    fun calcArea(): Double
}

interface ColoredShape2d : Shape2d {
    val borderColor: ColorOfRGBA
    val fillColor: ColorOfRGBA
}

class Circle(radius_1: Int, borderColor_1: ColorOfRGBA, fillColor_1: ColorOfRGBA) : ColoredShape2d {
    val radius: Int = radius_1
    var pi = 3.14
    override val borderColor: ColorOfRGBA = borderColor_1
    override val fillColor: ColorOfRGBA = fillColor_1
    override fun calcArea(): Double {
        return pi * radius * radius
    }
}

class Square(firstSide_1: Double, secondSide_1: Double, borderColor_1: ColorOfRGBA, fillColor_1: ColorOfRGBA) :
    ColoredShape2d {
    val firstSide = firstSide_1
    val secondSide = secondSide_1
    override val borderColor: ColorOfRGBA = borderColor_1
    override val fillColor: ColorOfRGBA = fillColor_1
    override fun calcArea(): Double {
        return firstSide * secondSide
    }
}

class Triangle(
    triangleFirstSide_1: Double,
    triangleSecondSide_1: Double,
    triangleThirdSide_1: Double,
    borderColorTriangle: ColorOfRGBA,
    fillColorTriangle: ColorOfRGBA
) :
    ColoredShape2d {
    val triangleFirstSide = triangleFirstSide_1
    val triangleSecondSide = triangleSecondSide_1
    val triangleThirdSide = triangleThirdSide_1
    override val borderColor: ColorOfRGBA = borderColorTriangle
    override val fillColor: ColorOfRGBA = fillColorTriangle
    override fun calcArea(): Double {
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
        } else error(("One of triangle sides is  0"))
        return square
    }
}

class ShapeCollector {
    private var figureList: ArrayList<ColoredShape2d> = arrayListOf()
    private var sumSquare: Double = 0.0
    fun addFigures(figure: ColoredShape2d) {
        figureList.add(figure)
    }

    fun listAllFigures(): ArrayList<ColoredShape2d> {
        return figureList
    }

    fun sumSquareFigures(): Double {
        val leftBorder = figureList.size - 1
        for (i in 0..leftBorder) {
            sumSquare += figureList[i].calcArea()
        }
        println(sumSquare)
        return sumSquare
    }

    fun sizeFigureList(): Int {
        return figureList.size
    }

    fun minSquare(): ColoredShape2d {
        val size = figureList.size - 1
        var minSquare = figureList[0].calcArea()
        var minFigure: ColoredShape2d = figureList[0]
        for (i in 0..size) {
            if (figureList[i].calcArea() <= minSquare) {
                minSquare = figureList[i].calcArea()
                minFigure = figureList[i]
            }
        }
        println("min: $minSquare")
        return minFigure
    }

    fun maxSquare(): ColoredShape2d {
        val size = figureList.size - 1
        var maxSquare = figureList[0].calcArea()
        var maxFigure: ColoredShape2d = figureList[0]
        for (i in 0..size) {
            if (figureList[i].calcArea() >= maxSquare) {
                maxSquare = figureList[i].calcArea()
                maxFigure = figureList[i]
            }
        }
        println("min: $maxSquare")
        return maxFigure
    }

    fun searchBorderColor(key: ColorOfRGBA): ArrayList<ColoredShape2d> {
        val listKeepFiguries: ArrayList<ColoredShape2d> = arrayListOf()
        val size = figureList.size - 1
        for (i in 0..size) {
            if (figureList[i].borderColor == key) {
                listKeepFiguries.add(figureList[i])
            }
        }
        return listKeepFiguries
    }

    fun mapFillColor(): Map<ColorOfRGBA, List<ColoredShape2d>> {
        return figureList.groupBy { it.fillColor }
    }

    fun mapBorderColor(): Map<ColorOfRGBA, List<ColoredShape2d>> {
        return figureList.groupBy { it.borderColor }
    }

    fun searchFillColor(key: ColorOfRGBA): ArrayList<ColoredShape2d> {
        val listKeepFiguries: ArrayList<ColoredShape2d> = arrayListOf()
        val size = figureList.size - 1
        for (i in 0..size) {
            if (figureList[i].fillColor == key) {
                listKeepFiguries.add(figureList[i])
            }
        }
        return listKeepFiguries
    }

    fun searchType(key: String): ArrayList<ColoredShape2d> {
        val typeList: ArrayList<ColoredShape2d> = arrayListOf()
        val size = figureList.size - 1
        for (i in 0..size) {
            if (figureList[i].javaClass.simpleName == key) {
                typeList.add(figureList[i])
            }
        }
        return typeList
    }
}