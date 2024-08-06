# PokedexApp

## Introduction

The PokedexApp allows users to view a list of pokemon from 1st generation with the ability for filter by name, id, and type. It features an individual screen displaying each pokemon, and a team screen, allowing the user to add and remove pokemon from the team.

<div style="display: flex; justify-content: center;">
<img src="../data/designs/Screenshots/UISearch.png" alt="Description of the image" width="500" height="500">
</div>


# Individual Pokemon page

* When the user click on a `specific Pokemon` in `List View` or `Team View` from the `right` side, `an introduction page` for `that Pokemon` will display on the `left` side.
<br><br>
![](IndiviPokePage/click_example.jpg)
<br><br>
* At the `top` is the `Pokemon's name`, followed by its `image` in the` middle`, and `below` are `ID, weight, height, types, and moves` details.
<br><br>
![](IndiviPokePage/page1.png)
<br><br>
![](IndiviPokePage/page2.png)
<br><br>
* `Clicking` on the `image` will `play` that `Pokemon's cry`.
<br><br>
![](IndiviPokePage/sound.jpg)
<br><br>
<audio controls>
<source src="cries_pokemon_latest_1.ogg">
</audio>

<br><br>
* The user can click on `moves panel` to view detail information. A `table` with the `name of the moves`, `accuracy`, `power`, `PP`, and `damage type` will be displayed on the screen.
<br><br>
![](IndiviPokePage/move_detail.png)
<br><br>
* When table closed, return to the general introduction page.
