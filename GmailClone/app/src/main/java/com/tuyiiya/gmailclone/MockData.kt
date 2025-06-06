package com.tuyiiya.gmailclone

import com.tuyiiya.gmailclone.model.Account
import com.tuyiiya.gmailclone.model.MailData

val mailList = listOf(
    MailData(
        mailId = 1,
        userName = "Christy",
        subject = "Weekly Android News",
        body = "Hello Christy we have got exciting addition  to the android api",
        timeStamp = "20:10"
    ),
    MailData(
        mailId = 2,
        userName = "Agatha",
        subject = "Job Application",
        body = "This is regarding a job opportunity I found on your website.",
        timeStamp = "20:10"
    ),
    MailData(
        mailId = 3,
        userName = "Cecilia",
        subject = "Flutter News",
        body = "Hello Cecilia we have got exciting addition  to the Flutter api",
        timeStamp = "20:10"
    ),
    MailData(
        mailId = 4,
        userName = "Angelo",
        subject = "Email regarding job opportunity",
        body = "This is regarding a job opportunity for the profile or android developer in our organisation.",
        timeStamp = "21:10"
    ),
    MailData(
        mailId = 5,
        userName = "Sam",
        subject = "New Event has been created",
        body = "A new event has been added to your dashboard go check it out",
        timeStamp = "20:10"
    ),
    MailData(
        mailId = 6,
        userName = "Medium",
        subject = "JetPack compose for beginners",
        body = "This article will teach you all the basics of jetpack compose.",
        timeStamp = "20:10"
    ),
    MailData(
        mailId = 7,
        userName = "Netflix",
        subject = "A new Device is using your account",
        body = "If you did not sign in with a new device then go to settings and reset your password",
        timeStamp = "20:10"
    ),
    MailData(
        mailId = 8,
        userName = "Collins",
        subject = "A request for partnership",
        body = "This is a follow up email about the partnership request",
        timeStamp = "20:50"
    ),
    MailData(
        mailId = 9,
        userName = "Amazon",
        subject = "Your account has been created",
        body = "If you did not sign in with a new device then go to settings and reset your password",
        timeStamp = "20:10"
    ),
    MailData(
        mailId = 10,
        userName = "Jobs",
        subject = "New opportunity that suits you",
        body = "We have an opening that suits your profile kindly check it out and indicate your interest",
        timeStamp = "20:10"
    )
)

val accountList = listOf<Account>(
    Account(icon =  R.drawable.profilepic,userName = "Tuyiiya Web",email = "tuyiiyaweb@yopmail.com",unReadMails = 99),
    Account(userName = "Chris Morty",email = "chris@gmail.com",unReadMails =80),
    Account(userName = "Jane Doe",email = "janedoe@gmail.com",unReadMails = 99)
)