import 'package:flutter/material.dart';
import 'package:grid_view_app/widgets.dart';

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
            title: Text('ListView widget'),
          ),
          body: GridViewExtentWidget(),
        ));
  }
}

/*
   Также есть вариант custom, если нужен - гуглить
 */

class GridViewExtentWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 300,
      color: Colors.greenAccent,
      child: GridView.extent(
        /*
          Контейнер шириной 300, макс размер ячейки - 90, вместиться 4 раза
          Вообщем не доконца понял - но адаптивно
         */
        maxCrossAxisExtent: 100,
        children: [
          TextWidget(text: "1"),
          TextWidget(text: "2"),
          TextWidget(text: "3"),
          TextWidget(text: "4"),
          TextWidget(text: "5"),
        ],
      ),
    );
  }
}


class GridViewCountWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView.count(
      crossAxisCount: 2,
      children: [
        TextWidget(text: "1"),
        TextWidget(text: "2"),
        TextWidget(text: "3"),
        TextWidget(text: "4"),
        TextWidget(text: "5"),
        TextWidget(text: "6"),
        TextWidget(text: "7"),
        TextWidget(text: "8"),
        TextWidget(text: "9"),
        TextWidget(text: "10"),
      ],
    );
  }
}

class GridViewBuilderWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView.builder(
        itemCount: 10,
        gridDelegate:
            SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
        itemBuilder: (context, index) {
          return TextWidget(text: '$index');
        });
  }
}

class GridViewWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView(
      gridDelegate:
          // Фиксированное кол-во столбцов
          SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 3),
      children: [
        TextWidget(text: "1"),
        TextWidget(text: "2"),
        TextWidget(text: "3"),
        TextWidget(text: "4"),
        TextWidget(text: "5"),
        TextWidget(text: "6"),
        TextWidget(text: "7"),
        TextWidget(text: "8"),
        TextWidget(text: "9"),
        TextWidget(text: "10"),
      ],
    );
  }
}
