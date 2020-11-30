
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

// константы можно будет вынести в отдельный файл (если нужно)
const kPrimaryColor = Colors.white;

const LargeTextSize = 26.0;
const MediumTextSize = 20.0;
const BodyTextSize = 14.0;

ThemeData WeatherTheme() => ThemeData(
  brightness: Brightness.dark,
  primaryColor: kPrimaryColor,

  textTheme: TextTheme(
    /*bodyText1: TextStyle(
      fontSize: kBodyTextSize,
      fontWeight: FontWeight.w400,
      color: kPrimaryColor
    )*/
    bodyText1: GoogleFonts.lato(
        textStyle: TextStyle(fontStyle: FontStyle.italic, fontSize: BodyTextSize)
    )
  )
);