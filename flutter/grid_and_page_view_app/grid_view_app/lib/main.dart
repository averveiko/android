import 'package:flutter/material.dart';

import 'grid_view_widgets.dart';

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
            title: Text('ListView and PageView widgets'),
          ),
          //body: GridViewExtentWidget(),
          body: PageViewWidget(),
        ));
  }
}

class PageViewBuilderWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return PageView.builder(
        itemCount: 5,
        itemBuilder: (context, index) {
          return Container(
            color: index.isEven ? Colors.red : Colors.greenAccent,
            alignment:  Alignment.center,
            child: Text('$index'),
          );
        }
    );
  }
}


class PageViewWidget extends StatelessWidget {
  // Пример этой штуки смотри в исходниках PageController
  final PageController controller = PageController(initialPage: 0);

  @override
  Widget build(BuildContext context) {
    return PageView(
      // Если нужен вертикальный скролл
      //scrollDirection: Axis.vertical,
      // Если не нужно полное полистывание
      //pageSnapping: false,
      //physics: NeverScrollableScrollPhysics(),
      // Позволяет управляет отображением страниц
      controller: controller,
      onPageChanged: (number) {
        print('Page number $number');
      },

      children: [
        Container(
          color: Colors.red,
          child: Center(
            child: Text('Stop!'),
          ),
        ),
        Container(
          color: Colors.orange,
          child: Center(
            child: Text('Ready?'),
          ),
        ),
        Container(
          color: Colors.green,
          child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
            Text('Go!', style: TextStyle(fontSize: 40)),
            RaisedButton(
                child: Text('Reload'),
                color: Colors.blue,
                onPressed: () {
                  // По нажатию перейти на первую страницу
                  //controller.jumpToPage(0); // мгновенный переход
                  controller.animateToPage(0,
                      duration: Duration(seconds: 1),
                      curve: Curves.easeIn);
                })
          ]),
        )
      ],
    );
  }
}
