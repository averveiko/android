import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Alexander Verveyko ToDo app',
        theme: ThemeData(
          primarySwatch: Colors.blue,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: ChangeNotifierProvider<TasksProvider>.value(
          value: TasksProvider(),
          child: MainScreen(),
        ));
  }
}

class MainScreen extends StatelessWidget {
  // TODO dispose?
  final _taskEditController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    TasksProvider _tasksProvider = Provider.of<TasksProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('ToDo'),
        centerTitle: true,
      ),
      body: TaskList(
        tasks: _tasksProvider.tasks,
      ),
      bottomSheet: TextFormField(
        onFieldSubmitted: (value) {
          onFieldSubmit(value, _tasksProvider);
        },
        autofocus: true,
        controller: _taskEditController,
        decoration: InputDecoration(
            labelText: 'new task:',
            hintText: 'put new task text here',
            suffixIcon: IconButton(
              icon: Icon(Icons.send),
              onPressed: () {
                String value = _taskEditController.text.toString();
                onFieldSubmit(value, _tasksProvider);
              },
            )),
      ),
    );
  }

  void onFieldSubmit(String value, TasksProvider _tasksProvider) {
    if (value.isNotEmpty) {
      _tasksProvider.add(Task(title: value));
      _taskEditController.clear();
    }
  }
}

class TaskList extends StatelessWidget {
  final List<Task> tasks;

  TaskList({this.tasks});

  @override
  Widget build(BuildContext context) {
    TasksProvider _tasksProvider = Provider.of<TasksProvider>(context);

    return ListView.builder(
        itemCount: tasks.length,
        itemBuilder: (context, index) {
          Icon icon = tasks[index].completed ? Icon(Icons.check_circle) : Icon(
              Icons.circle);
          return ListTile(
              leading: IconButton(
                icon: icon,
                onPressed: () => _tasksProvider.changeCompleted(index),
              ),
              title: Text(tasks[index].title));
        }
    );
  }
}

class Task {
  String title;
  bool completed = false;

  Task({String title, bool completed}) {
    completed == null ? this.completed = false : this.completed = completed;
    this.title = title;
  }
}

List<Task> createDemoTaskList() {
  return [
    Task(title: 'One'),
    Task(title: 'Two'),
    Task(title: 'Three'),
  ];
}

class TasksProvider extends ChangeNotifier {
  List<Task> _tasks = List<Task>();

  List<Task> get tasks => _tasks;

  void add(Task task) {
    _tasks.add(task);
    notifyListeners();
  }

  void changeCompleted(int index) {
    _tasks[index].completed = !_tasks[index].completed;
    notifyListeners();
  }
}
