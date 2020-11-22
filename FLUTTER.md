#### Заметки по Flutter

```Dart
final TextStyle _biggerFont = TextStyle(fontSize: 18.0);
Text(pair.asPascalCase, style: _biggerFont)
```
------------------
Пример простого списка:

Можно просто готовый список элементов передать (если список небольшой)
```Dart
 return ListView(children: []);
```
```Dart
class ListViewBuilderWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: 20, // Не обязательно, можно бесконечный ленивый список
        itemBuilder: (context, index) {
          return TextWidget(text: '$index');
        }
    );
  }
}
```
Или так (сепарированный):
```Dart
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
```
Или так:
```Dart
class DemoList extends StatelessWidget {
  final TextStyle _biggerFont = TextStyle(fontSize: 18.0);
  final List<String> _list = ['One', 'Two', 'Three'];

  @override
  Widget build(BuildContext context) {

    final tiles = _list.map((e) {
      return ListTile(title: Text(e, style: _biggerFont));
    });

    final divided = ListTile.divideTiles(
        context: context, // обязательно
        tiles: tiles
    ).toList(); // тоже обязательно

    return Scaffold(
      appBar: AppBar(
        title: Text('Demo List'),
      ),
      body: ListView(children: divided),
    );
  }
}
```
Есть еще конструктор ListView.custom - если надо - гугли
------------------

* Column(children: <Widget>[]) - позволяет разположить детей вертикально друг под другом (столбец). По дефолту выравниваются относительно центра друг друга.
* Row(children: <Widget>[]) - позволяет разположить детей горизонтально

* RaisedButton(onPressed (){}, child: Text('press me'))