# HTML 
HTML : HyperText Markup Language

####HTML 이란
웹 문서의 구조를 표하는 데에 사용되는 마크업 언어
CERN에서 일하던 Tim-Berners Lee 아조씨가 만들었음

#### HTML 간단한 예제
```html
<!DOCTYPE html> <!-- 이 문서가 HTML임을 명시 -->
<html> <!-- HTML 문서의 시작 -->
<head> <!-- 이 페이지와 관련된 들어감, 컨텐츠는 X -->
<title>Page Title</title> <!-- 문서 타이틀 -->
</head> <!-- HEAD의 끝  -->
<body> <!-- BODY : 문서의 컨텐츠가 들어가는 부분(?) -->
<h1>This is a Heading</h1> <!-- 제목을 표시할 때 쓰는 h태그 -->
<p>This is a paragraph.</p> <!--단락을 표시하는 p태그  -->
</body> <!--BODY의 끝 -->
</html> <!-- HTML 문서의 끝 -->

```
&lt;html&gt;처럼 꺽쇠기호로 둘러싸인 것을 태그라고 하는데, 태그는 <태그이름>, </태그이름>으로 열고 닫을 수 있다. (아닌 태그도 있다.) 이렇게 열고 닫을 수 있는 태그들은 글, 제목과 같은 컨텐츠의 범위를 정해줄 수 있다.

#### HTML의 버전
현재 (2014년 이후)에는 HTML5가 사용된다. 다른 버전들은 여기서 언급하기엔 TMI이니 패스

#### HTML 문서는 무엇으로 작성해야할까
사실 정답은 없다. 메모장(notepad)으로도 HTML을 공부할 수 있다. 
http://archive.is/djFxD  -  에디터 비교글

#### 간단한 태그들

**CodePen으로 시각적으로 보여주고 싶었으나 Github-Flavored Markdown이 지원하지 않는다 ㅜ.ㅜ 그냥 링크를 눌러주자**
##### 1.  h1~h5태그 (heading)

```
<h1>h는 제목(heading)을 나타낸다.</h1>
<h2>숫자가 커질수록</h2>
<h3>점점</h3>
<h4>글자가 작아진다, 숫자가 클수록 중요한 제목!</h4>
```

https://codepen.io/hygoni/pen/xxbKZMG

##### 2. p (paragraph) 태그
```
<p>여기에는 글을 쓸 수 있다!</p>
```

https://codepen.io/hygoni/pen/RwNbrdg

##### 3. a 태그
```
<a href="https://google.com">글, 이미지 등등에 링크를 걸려면 a로 둘러싸버리자</a>
```

https://codepen.io/hygoni/pen/JjoPGVG

##### 4. img 태그

```
  <img src="https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png" alt="이미지에 마우스를 올려보자">

여기서 img태그 속성은
src="주소"
alt="이미지설명"
```
https://codepen.io/hygoni/pen/dyPbGEO

**더 많지만  귀찮으므로 패스. HTML 태그들은 검색하며 많이 나온다.**

#### HTML의 속성
속성이란 말이 사물의 특징, 성질을 의미하듯이 HTML 태그에도 특징을 부여해줄 수 있다.   예를 들어 아까 img 태그로 이미지를 불러왔다면, 크기 속성을 추가해 크기를 조절해줄 수 있다.

```
  <img src="https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png" width="100px" height="100px">
  <!-- 그리고 사실 src도 속성이다 -->
  
  <태그이름 속성="속성">
위처럼 생긴건 다 속성임.
```
<iframe height="265" style="width: 100%;" scrolling="no" title="KKwPVjJ" src="https://codepen.io/hygoni/embed/KKwPVjJ?height=265&theme-id=dark&default-tab=result" frameborder="no" allowtransparency="true" allowfullscreen="true">
  See the Pen <a href='https://codepen.io/hygoni/pen/KKwPVjJ'>KKwPVjJ</a> by Jade Yu
  (<a href='https://codepen.io/hygoni'>@hygoni</a>) on <a href='https://codepen.io'>CodePen</a>.
</iframe>

**다 정리하긴 내용이 많아요 ㅜㅜㅜ 
옛날에 썼던 강좌 링크 던지고 튀겠습니다 흨흨
https://blog.naver.com/dbgudrhs1/90174972698
**
