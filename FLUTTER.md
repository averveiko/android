#### Заметки по Flutter

```Dart
final TextStyle _biggerFont = TextStyle(fontSize: 18.0);
Text(pair.asPascalCase, style: _biggerFont)
```
------------------
Пример простого списка:
```Dart
class ToDoList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('My simple list view')),
      body: _buildListView(),
    );
  }

  ListView _buildListView() {
    return ListView.builder(
      padding: EdgeInsets.all(16.0),
      itemBuilder: (context, i) {
        if (i.isOdd) return Divider();
        return _buildRow(i);
      },
    );
  }

  Widget _buildRow(int i) {
    // Всего 29 элементов (включая разделители)
    if (i == 30) return null;
    return ListTile(
      title: Text('Item $i', style: TextStyle(fontSize: 18.0)),
    );
  }
}```
Или так (наверно правильнее):
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
------------------
