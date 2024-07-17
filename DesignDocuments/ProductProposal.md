# Product Proposal

## Initial Design

Update UML here

### Generic UML

```mermaid
---
title: Initial Diagram
---
classDiagram
  note "Generic UML to get started"
  IView <|-- View
  IModel <|-- Model
  note for Model "Data logic, communicates with a local database"
  NetUtils
  note for NetUtils "Concerns with API calls if used"
  IController <|-- Controller
  Controller --> NetUtils : uses
  Controller --> Model : uses
  View --> Controller : uses
  note for View "Displays data, communicates with the Controller"
```

## About
* What are you building?
  - crud app

* What are the initial features for the application?

* What are the *minimum* additional features you plan to implement?

* What are your stretch goals (features beyond the minimum)?
* Go over your initial design.

  * Special emphasis should be placed on how you plan to break it up
  * MVC, presenter, file management, different input validation, testing, documentation, etc.

* How do you plan to break up the work?

* What is your teams timeline and major check-in points?


### Professor's notes:
* Pick which feature you want to implement
* Create a branch for that feature/aspect of the project
* Work on that aspect.
* Do a pull request when done (while you were working in your branch)
* Have someone else go over your code to review and merge back into main
* Delete the branch
* Repeat for another feature/aspect as you grow the assignment
