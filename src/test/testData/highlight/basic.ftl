# Message References
hello   = Hello, world!
welcome = Welcome, { $user }!
time-elapsed = Time elapsed: { NUMBER($duration, maximumFractionDigits: 0) }s.
-brand-name = Firefox
installing = Installing { -brand-name }.

# Selectors
your-score = You scored {
    NUMBER($score, minimumFractionDigits: 1) ->
        [0.0]   zero points. What happened?
       *[other] { NUMBER($score, minimumFractionDigits: 1)} points.
    }

# Attributes
login-input = Predefined value
    .placeholder = email@example.com
    .aria-label = Login input value
    .title = Type your login email

# Multiline and indent
multi = Text can also span multiple lines as long as
    each new line is indented by at least one space.
    Because all lines in this message are indented
    by the same amount, all indentation will be
    removed from the final value.

indents =
    Indentation common to all indented lines is removed
    from the final text value.
      This line has 2 spaces in front of it.

