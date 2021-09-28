# kotlinDemo
Demo app that consumes a Fake REST API with clean architecture and MVVM

This application have three screens. The first is a splash screen that preload data from server and save it in database.
![Splash screen](https://media.giphy.com/media/vPohqTeshgjI1gH5tG/giphy.gif?cid=790b76112c8554532ffe8f4672ab75006fab35b0b48eab66&rid=giphy.gif&ct=g)

The second screen is the main.This screen shows a recycler view with the user list in cardviews. The cardviews have two actions, click or swipe.
When the cardview is clicked, you're redirected to detail screen. Instead, when cardview is swiped, the associated user is deleted from database and server. 
Other function in this screen is a floating button. When it's clicked, detail screen is launched to adding a new user.

![Main screen - List](https://media.giphy.com/media/MjbSLSypJFn0ZbVRUr/giphy.gif?cid=790b76110e891d09cb2618c780d6b99fd29e74d8430619b1&rid=giphy.gif&ct=g)
![Main screen - OnClick](https://media.giphy.com/media/5p19ZoXUZvWaeULU6S/giphy.gif?cid=790b7611a3310825ab31217b98235cfa93ae67a365e96460&rid=giphy.gif&ct=g)
![Main screen - Delete](https://media.giphy.com/media/QFOfg5q07QPv3sQjLT/giphy.gif?cid=790b76112b858cca0c8a70b013d30f44225f0386e4cafd91&rid=giphy.gif&ct=g)


The third screen is the detail screen. This screen shows many inputs with all user data. 
If it comes from a selected card view, data is loaded and setted in the disabled inputs, but instead, if it comes from the floating button, all inputs are empty and enabled. It is possible to modify the data of a user with the menu actions, which enable and disable the inputs and allow them to be saved later.

![Detail screen - Actions](https://media.giphy.com/media/cGzEj3f0IT7XpNnXT0/giphy.gif?cid=790b76112d4b2bb34b73d0b808fc4818acbf07b1e7809f99&rid=giphy.gif&ct=g)
