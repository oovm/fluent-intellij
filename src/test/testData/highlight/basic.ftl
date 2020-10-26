# 2
hello = Hello, world!
welcome = Welcome, { $user }!

time-elapsed = Time elapsed: { $duration }s.
time-elapsed = Time elapsed: { NUMBER($duration, maximumFractionDigits: 0) }s.

multi = Text can also span multiple lines as long as
    each new line is indented by at least one space.
    Because all lines in this message are indented
    by the same amount, all indentation will be
    removed from the final value.

indents =
    Indentation common to all indented lines is removed
    from the final text value.
      This line has 2 spaces in front of it.


unread-emails = { $user } has { $email-count } unread emails.