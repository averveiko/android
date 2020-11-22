import 'package:flutter/material.dart';
import 'package:list_view_app/widgets.dart';

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
          body: ListViewSeparatedWidget(),
        ));
  }
}

class SimpleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView(
      // Если нужен внутренний отступ
      padding: EdgeInsets.all(10),
      // Если нужен горизонтальный скроллинг
      // scrollDirection: Axis.horizontal,
      // Обратить направление отрисовки
      reverse: true,
      // Если нужно проскролить сразу на 100
      controller: ScrollController(initialScrollOffset: 100),
      // Физика прокрутки (отключить в данном случае)
      physics: NeverScrollableScrollPhysics(),
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

class ListViewBuilderWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: 20, // Не обязательно, можно бесконечный ленивый список
        itemBuilder: (context, index) {
          return TextWidget(text: '$index');
        });
  }
}

class ListViewSeparatedWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView.separated(
        itemBuilder: (context, index) {
          return TextWidget(text: '$index');
        },
        separatorBuilder: (context, index) {
          return Divider(color: Colors.black);
        },
        itemCount: 20);
  }
}
