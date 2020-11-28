import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class ListViewExampleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              title: Text('Layout example'),
              centerTitle: true,
            ),
            body: Container(
              child: UseItListView(),
            )));
  }
}

class UseItListView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView(
      //shrinkWrap: true, // Если не надо заполнять экран - плохо влияет на производительность
      //itemExtent: 300, // Размер айтема ( ширина для Axis.horisontal или высота для Axis.vertical. Повышает производительность
      padding: EdgeInsets.all(8),
      children: [
        ListTile(
          leading: Icon(Icons.ac_unit_sharp),
          title: Text('Вервейко'),
          subtitle: Text('Александр'),
          trailing: Icon(Icons.keyboard_arrow_right),
        ),
        ListTile(
          leading: Icon(Icons.wb_sunny),
          title: Text('Иванов'),
          subtitle: Text('Иван'),
          trailing: Icon(Icons.keyboard_arrow_right),
        ),
        ListTile(
          leading: Icon(Icons.wb_cloudy_outlined),
          title: Text('Some'),
          subtitle: Text('thing'),
          trailing: Icon(Icons.keyboard_arrow_right),
        ),
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
          return Text('$index');
        });
  }
}

class ListViewSeparatedWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView.separated(
        itemBuilder: (context, index) {
          return Text('$index');
        },
        separatorBuilder: (context, index) {
          return Divider(color: Colors.black);
        },
        itemCount: 20);
  }
}

class SimpleListViewWidget extends StatelessWidget {
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
        Text("1"),
        Text("2"),
        Text("3"),
        Text("4"),
        Text("5"),
        Text("6"),
        Text("7"),
        Text("8"),
        Text("9"),
        Text("10"),
      ],
    );
  }
}
