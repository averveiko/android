import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Column and row widgets'),
        ),
        body: SimpleWidget(),
      )
    );
  }
}

class SimpleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      // расположение вначале(start), в середине(center) или в конце контейнера (end)
      // spaceBetween - равномерно
      // spaceAround - тоже вариация равномерного (см картинку  гугле)
      // spaceEvenly - равномерно включая пространство до и после
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      // Сколько места должно быть занято в главной оси (макс по дефолту (типо fill_Parent)),
      // если мин - то размер будет равен сумме размеров всех элементов без пустот между ними
      mainAxisSize: MainAxisSize.max,
      // Выравнивание по оси, перпендикулярной главной оси (по дефолту center)
      // stretch - заполнить все доступное пространство по перпендикулярной оси
      // baseline - по базовой линии, но в этом случае надо указать textBaseline: TextBaseline.alphabetic,
      crossAxisAlignment: CrossAxisAlignment.end,
      children: [
        TextWidget(color: Colors.limeAccent, width: 120, text: "First", fontSize: 50),
        TextWidget(color: Colors.cyanAccent, width: 180, text: "Second", fontSize: 40),
        TextWidget(color: Colors.purpleAccent, width: 100, text: "Third", fontSize: 30)
      ],
    );
  }
}

class TextWidget extends StatelessWidget {
  const TextWidget({Key key, this.text, this.color, this.width, this.fontSize})
      : super(key: key);

  final String text;
  final Color color;
  final double width;
  final double fontSize;

  @override
  Widget build(BuildContext context) {
    return Container(
      width: width,
      color: color,
      child: Text(
        text,
        style: TextStyle(fontSize: fontSize),
      ),
    );
  }
}



