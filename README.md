# Software Studio Assignment 6

## Notes
+ You will have to import the libraries on your own. All the libraries are provided on iLMS.
+ Even you followed the design TA shown in the video, you still have to explain the control of the program in the next section.

## Explanation of the Design
一開始我們是分開寫
李家揚 負責一開始整個東西的架構 包括把所有圓圈放上去還有大圈圈
還有滑鼠按下的拖曳 但是還沒有很順的動畫
接著李岳容負責鼠標放到character上時會出現名字的label 還有圓圈變大
還有把Character拖曳圓圈內時大圓會變粗
接著寫character彼此的連線。李岳容的作法是 在mainApplet裡面用ArryList記錄誰是
在圓圈內的character，而且每個Chracter也都有私人變數 去紀錄自己是不是在大圓內
然後把整個MainApplet中記錄在大圓內的ArrayList傳到Net裡面用for去跑 然後跑每個人的每個target
如果target也在圓裡面 就要畫線
但李家揚也寫了這部分，但是她沒有用net 最後因李家揚的版本較好寫動畫所以採用他的
原本只是判斷圓在裡面 而後李家揚寫了函數 讓圓可以以正多邊形擺上去
之後他加了兩個按鈕，還有回去的動畫
方法是，都先記錄原本character的位置，然後減掉現在的位置，然後每次display都移現在差值的0.9
也就是說會一開始移得很慢 後來很快
還有兩個按鈕是利用ControlP5來新增按鈕

Example:
### Operation
+ Clicking on the button "Add All": users can add all the characters into network to be analyzed.
+ Clicking on the button "Clear": users can remove all the characters from network.
+ Hovering on the character: the name of the character will be revealed.
+ By dragging the little circle and drop it in the big circle, the character will be added to the network.
+ By pressing key 1~7 on the keyboard, users can switch between episodes.
+ ...etc.

### Visualization
+ The width of each link is visualized based on the value of the link.
+ ...etc.
