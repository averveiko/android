import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FormExampleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              leading: Icon(Icons.format_shapes),
              title: Text(
                "Form Example",
              ),
              centerTitle: true,
            ),
            body: RegisterForm()));
  }
}

class RegisterForm extends StatefulWidget {
  @override
  _RegisterFormState createState() => _RegisterFormState();
}

class _RegisterFormState extends State<RegisterForm> {
  final _formKey = GlobalKey<FormState>();

  bool _hidePass = true;

  final _nameController = TextEditingController();
  final _phoneController = TextEditingController();
  final _emailController = TextEditingController();
  final _storyController = TextEditingController();
  final _passController = TextEditingController();
  final _confirmPassController = TextEditingController();

  List<String> _countries = ['Russia', 'Ukraine', 'Germany', 'France'];
  String _selectedCountry;

  final _nameFocus = FocusNode();
  final _phoneFocus = FocusNode();
  final _passFocus = FocusNode();

  @override
  void dispose() {
    _nameController.dispose();
    _phoneController.dispose();
    _emailController.dispose();
    _storyController.dispose();
    _passController.dispose();
    _confirmPassController.dispose();

    _nameFocus.dispose();
    _phoneFocus.dispose();
    _passFocus.dispose();
  }

  void _fieldFocusChange(BuildContext context, FocusNode currentFocus, FocusNode nextFocus) {
    currentFocus.unfocus();
    FocusScope.of(context).requestFocus(nextFocus);
  }

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey, // Необходимо для валидации
      child: ListView(
        padding: EdgeInsets.all(16),
        children: [
          TextFormField(
            focusNode: _nameFocus,
            autofocus: true, // При появлении формы поставить сюда фокус
            onFieldSubmitted: (_) { // Когда после воода нажали "ентер"
              _fieldFocusChange(context, _nameFocus, _phoneFocus);
            },
            controller: _nameController,
            //validator: (val) => val.isEmpty ? 'Name is required' : null,
            validator: _validateName,
            decoration: InputDecoration(
              labelText: 'Full name *',
              hintText: 'What do people call you',
              prefixIcon: Icon(Icons.person),
              suffixIcon: Icon(
                Icons.delete_outline,
                color: Colors.red,
              ),
              enabledBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.black, width: 2)),
              focusedBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.blue, width: 2)),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            focusNode: _phoneFocus,
            onFieldSubmitted: (_) { // Когда после воода нажали "ентер"
              _fieldFocusChange(context, _phoneFocus, _passFocus);
            },
            controller: _phoneController,
            validator: (value) => _validatePhoneNumber(value)
                ? null
                : 'Phone number must be entered as (###)### - ###',
            keyboardType: TextInputType.phone,
            inputFormatters: [
              //FilteringTextInputFormatter.digitsOnly,
              FilteringTextInputFormatter(RegExp(r'^[()\d -]{1,15}$'),
                  allow: true),
            ],
            decoration: InputDecoration(
              labelText: 'Phone number *',
              hintText: 'Where we can reach you',
              helperText: 'Phone format: (XXX)XXX-XXXX',
              prefixIcon: Icon(Icons.call),
              suffixIcon: Icon(
                Icons.delete_outline,
                color: Colors.red,
              ),
              enabledBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.black, width: 2)),
              focusedBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.blue, width: 2)),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            controller: _emailController,
            validator: _validateEmail,
            keyboardType: TextInputType.emailAddress,
            decoration: InputDecoration(
                labelText: 'Email address',
                hintText: 'Enter a email address',
                icon: Icon(Icons.mail)),
          ),
          SizedBox(
            height: 10,
          ),
          // Выпадающий список
          DropdownButtonFormField(
            //validator: (val) => val == null ? 'Country is required' : null,
            items: _countries.map((country) {
              return DropdownMenuItem(child: Text(country), value: country);
            }).toList(),
            onChanged: (country){
              print(country);
              setState(() {
                _selectedCountry = country;
              });
            },
            value: _selectedCountry,
            decoration: InputDecoration(
                border: OutlineInputBorder(),
                icon: Icon(Icons.map),
                labelText: 'Country?'),
          ),
          SizedBox(
            height: 20,
          ),
          TextFormField(
            controller: _storyController,
            inputFormatters: [
              // Макс 100 символов
              LengthLimitingTextInputFormatter(100),
            ],
            decoration: InputDecoration(
              labelText: 'Life story',
              hintText: 'Tell us about your self',
              helperText: 'Keep it short, this is just a demo',
              border: OutlineInputBorder(),
            ),
            maxLines: 3,
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            focusNode: _passFocus,
            controller: _passController,
            validator: _validatePass,
            obscureText: _hidePass,
            maxLength: 8,
            decoration: InputDecoration(
              labelText: 'Password *',
              hintText: 'Enter the password',
              icon: Icon(Icons.security),
              suffixIcon: IconButton(
                icon: Icon(_hidePass ? Icons.visibility : Icons.visibility_off),
                onPressed: () {
                  setState(() {
                    _hidePass = !_hidePass;
                  });
                },
              ),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            controller: _confirmPassController,
            validator: _validatePass,
            obscureText: _hidePass,
            maxLength: 8,
            decoration: InputDecoration(
              labelText: 'Confirm password *',
              hintText: 'Confirm the password',
              icon: Icon(Icons.border_color),
            ),
          ),
          SizedBox(
            height: 15,
          ),
          RaisedButton(
            onPressed: _onSubmitForm,
            color: Colors.green,
            child: Text(
              'Submit form',
              style: TextStyle(color: Colors.white),
            ),
          )
        ],
      ),
    );
  }

  bool _validatePhoneNumber(String phone) {
    final _phoneExp = RegExp(r'^\(\d\d\d\)\d\d\d-\d\d\d\d$');
    return _phoneExp.hasMatch(phone);
  }

  String _validateEmail(String email) {
    if (email.isEmpty) {
      return 'Email is required';
    } else if (!email.contains('@')) {
      return 'Please enter valid email';
    }
    return null;
  }

  String _validateName(String name) {
    final _nameExp = RegExp(r'^[A-Za-z]+$');
    if (name.isEmpty) {
      return 'Name is required';
    } else if (!_nameExp.hasMatch(name)) {
      return 'Please enter alphavetical chatacters only';
    }
    return null;
  }

  String _validatePass(String pass) {
    if (pass.length != 8) {
      return '8 characters required';
    } else if (pass != _confirmPassController.text) {
      return 'Password does not match';
    }
    return null;
  }

  _onSubmitForm() {
    if (_formKey.currentState.validate()) {
      _formKey.currentState.save();
      print('Form is valid');
      // Вывод вы консоль содержимого текстовых полей
      print('Name: ${_nameController.text}');
      print('Phone: ${_phoneController.text}');
      print('Email: ${_emailController.text}');
      print('Country: ${_selectedCountry}');
      print('Story: ${_storyController.text}');
      print('Pass: ${_passController.text}');
      print('Confirm: ${_confirmPassController.text}');
    } else {
      print('Form is not valid!');
    }
  }
}
