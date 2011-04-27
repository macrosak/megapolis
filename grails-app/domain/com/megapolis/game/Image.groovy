package com.megapolis.game

class Image {

    int height
    int width
    int offsetX
    int offsetY
    String filename

    List mapx
    List mapy

    static hasMany = [mapx: Integer, mapy: Integer]
}

