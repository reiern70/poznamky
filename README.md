Hello dear friends,

I am sorry but this project has a lot of nested classes what you don't need. I give you little instructions where you can find Slider compoent and slider Javascript, project is deployable.. At this moment I cannot clean my project and do it cleaner, sorry.

Instructions:
In src/main/java/myapp/conversation/components/Slider.java I have little implementation of Slider Range component, from where I am handling Ajax onChange event. On line 37 I am adding TextField. 

Slider component is added to HomePage in src/main/java/myapp/conversation/screens/HomePage.java, but I think you don't need it

In src/main/webapp/assets/slider/PacolSlider.js I have my client side code of Range Slider behavior, in initSlider function on line 92 I am changing input value of TextField
