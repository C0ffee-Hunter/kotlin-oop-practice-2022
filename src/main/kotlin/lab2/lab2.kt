package main.kotlin.lab2

import kotlin.math.sqrt

fun main() {
    val firstColor = Colors(2, 123, 56, 0.31)
    val secondColor = Colors(3, 4, 56, 0.23)
    val thirdColor = Colors(45, 234, 12, 1.0)
    val fourthColor = Colors(5, 103, 78, 0.78)
    val circle = Circle(2, firstColor, secondColor)
    val firstSquare = Square(12.0, 15.0, thirdColor, firstColor)
    val secondSquare = Square(6.0, 13.0, fourthColor, secondColor)
    val triangle = Triangle(6.0, 13.0, 23.0, fourthColor, secondColor)
    val figureList = ShapeCollector()

    figureList.addFigures(circle)
    figureList.addFigures(firstSquare)
    figureList.addFigures(secondSquare)
    figureList.addFigures(triangle)

    //sum of all figure
    figureList.sumSquareFigures()
    //list of all stored figure
    figureList.listAllFigures()
    //ашпгку list size
    figureList.sizeFigureList()
    //Returning a figure with a min area
    figureList.minSquare()
    //Returning a figure with a max area
    figureList.maxSquare()
    //return shapes grouped by fill color
    figureList.mapFillColor()
    //return shapes grouped by border color
    figureList.mapBorderColor()
    //find all shapes by border color
    figureList.searchBorderColor(thirdColor)
    //find all shapes by fill color
    figureList.searchFillColor(secondColor)
    //finding shapes by a specific type
    figureList.searchType()

}

data class Colors(val red: Int, val green: Int, val blue: Int, val alpha: Double) {
    override fun toString(): String {
        return "Color figure of RGBA: $red, $green, $blue, $alpha"
    }
}

interface Shape2d {
    fun calcArea(): Double
}

interface ColoredShape2d : Shape2d {
    val borderColor: Colors
    val fillColor: Colors
}

class Circle(radius: Int, borderColorCircle: Colors, fillColorCircle: Colors) : ColoredShape2d {
    val classRadius: Int = radius
    var pi = 3.14
    override val borderColor: Colors = borderColorCircle
    override val fillColor: Colors = fillColorCircle
    override fun calcArea(): Double {
        return pi * classRadius * classRadius
    }
}

class Square(firstSide: Double, secondSide: Double, borderColorSquare: Colors, fillColorSquare: Colors) :
    ColoredShape2d {
    val classFirstSide = firstSide
    val classSecondSide = secondSide
    override val borderColor: Colors = borderColorSquare
    override val fillColor: Colors = fillColorSquare
    override fun calcArea(): Double {
        return classFirstSide * classSecondSide
    }
}

class Triangle(
    triangleFirstSide: Double,
    triangleSecondSide: Double,
    triangleThirdSide: Double,
    borderColorTriangle: Colors,
    fillColorTriangle: Colors
) :
    ColoredShape2d {
    val triangleFirstSide = triangleFirstSide
    val triangleSecondSide = triangleSecondSide
    val triangleThirdSide = triangleThirdSide
    override val borderColor: Colors = borderColorTriangle
    override val fillColor: Colors = fillColorTriangle
    override fun calcArea(): Double {
        val square: Double
        // check triangle sides
        if (triangleFirstSide > 0.0 && triangleSecondSide > 0.0 && triangleThirdSide > 0.0) {
            if (triangleFirstSide + triangleSecondSide > triangleThirdSide //checking the sum of the first two sides
                && triangleFirstSide + triangleThirdSide > triangleSecondSide //checking the sum of the first and third side
                && triangleSecondSide + triangleThirdSide > triangleFirstSide //checking the sum of the second and third side
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

    fun searchBorderColor(key: Colors): ArrayList<ColoredShape2d> {
        val listKeepFiguries: ArrayList<ColoredShape2d> = arrayListOf()
        val size = figureList.size - 1
        for (i in 0..size) {
            if (figureList[i].borderColor == key) {
                listKeepFiguries.add(figureList[i])
            }
        }
        return listKeepFiguries
    }

    fun mapFillColor(): Map<Colors, List<ColoredShape2d>> {
        return figureList.groupBy { it.fillColor }
    }

    fun mapBorderColor(): Map<Colors, List<ColoredShape2d>> {
        return figureList.groupBy { it.borderColor }
    }

    fun searchType(): Map<Class<Any>, List<ColoredShape2d>> {
       return figureList.groupBy { it.javaClass }
    }

    fun searchFillColor(key: Colors): ArrayList<ColoredShape2d> {
        val listKeepFiguries: ArrayList<ColoredShape2d> = arrayListOf()
        val size = figureList.size - 1
        for (i in 0..size) {
            if (figureList[i].fillColor == key) {
                listKeepFiguries.add(figureList[i])
            }
        }
        return listKeepFiguries
    }
}
