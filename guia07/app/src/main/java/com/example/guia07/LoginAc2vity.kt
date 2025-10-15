package com.example.guia07

private lateinit var etEmail: EditText
private lateinit var etPass: EditText
private lateinit var btnLogin: Button
private lateinit var tvRedirectSignUp: TextView
private lateinit var tvLogingoogle:TextView
private lateinit var googleSignInClient:GoogleSignInClient
private lateinit var auth: FirebaseAuth
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    btnLogin = findViewById(R.id.button_login)
    etEmail = findViewById(R.id.editTextTextEmailAddress)
    etPass = findViewById(R.id.editTextTextPassword)
    tvRedirectSignUp= findViewById(R.id.tvRedirectSignUp)
    tvLogingoogle = findViewById(R.id.textView_login_google)
    auth = FirebaseAuth.getInstance()
    btnLogin.setOnClickListener{
        login()
    }
    tvRedirectSignUp.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        // using finish() to end the activity
        finish()
    }
}