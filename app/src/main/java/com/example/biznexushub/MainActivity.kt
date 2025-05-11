
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.biznexushub

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizNexusApp()
        }
    }
}

@Composable
fun BizNexusApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("features") { FeaturesScreen(navController) }
        composable("post") { PostCollaborationScreen(navController) }
        composable("contact") { ContactScreen(navController) }
        composable("about") { AboutScreen(navController) }
        composable("dashboard") { DashboardScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("discover") { DiscoverScreen(navController) }
    }
}


@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Welcome, [UserName]", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Role: Freelancer", color = Color.White, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Recent Collaborations:", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Text("- Collaboration with Jane Doe on AI project", modifier = Modifier.padding(8.dp))
            }
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Text("- Joined SME Startup pitch team", modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("profile") }, modifier = Modifier.fillMaxWidth()) {
                Text("View My Profile")
            }
            Button(onClick = { navController.navigate("discover") }, modifier = Modifier.fillMaxWidth()) {
                Text("Discover Collaborators")
            }
            Button(onClick = { navController.navigate("post") }, modifier = Modifier.fillMaxWidth()) {
                Text("New Collaboration")
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(16.dp))

            // Name input
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Description / Bio input
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Bio / Description", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Skills input
            OutlinedTextField(
                value = skills,
                onValueChange = { skills = it },
                label = { Text("Skills", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Experience input
            OutlinedTextField(
                value = experience,
                onValueChange = { experience = it },
                label = { Text("Experience Summary", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Save logic */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Save Profile")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("dashboard") }, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Dashboard")
            }
        }
    }
}


@Composable
fun DiscoverScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Discover", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))

            // Search input with white text
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Trending Tags: #AI, #Startup, #Mentor", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Jane Doe - Startup Founder")
                    Text("Looking for AI developers")
                    Button(onClick = { /* Connect */ }) {
                        Text("Connect")
                    }
                }
            }
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("John Smith - Mentor")
                    Text("Offering guidance on MVP building")
                    Button(onClick = { /* Connect */ }) {
                        Text("Connect")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("dashboard") }, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Dashboard")
            }
        }
    }
}



@Composable
fun LogoHeader() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = Modifier.size(100.dp)
    )
}

@Composable
fun SplashScreen(navController: NavHostController) {
    // Use a LaunchedEffect to delay for 2 seconds, then navigate to login
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true } // remove splash from backstack
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LogoHeader()
            Spacer(modifier = Modifier.height(16.dp))
            Text("BizNexus Hub", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeader()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Login", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
            Text("Log In", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Don't have an account? Sign up", modifier = Modifier.clickable { navController.navigate("signup") }, color = Color.Blue)
    }
}

@Composable
fun SignUpScreen(navController: NavHostController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeader()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Sign Up", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
        OutlinedTextField(value = role, onValueChange = { role = it }, label = { Text("Role (Freelancer/Startup/SME)") },
            modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            showSuccess = true
            navController.navigate("home")
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), modifier = Modifier.fillMaxWidth()) {
            Text("Create Account", color = Color.White, fontWeight = FontWeight.Bold)
        }
        if (showSuccess) {
            Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}



@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("About", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LogoHeader()
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "About BizNexus Hub",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "BizNexus Hub is designed to bridge the gap between freelancers, startups, and SMEs by offering a smart collaboration platform.\n\nWe provide tools for networking, matching with the right partners, and discovering projects or talent seamlessly.",
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { navController.navigate("features") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Get More", color = Color.White)
                }
            }
        }
    }
}




@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Welcome to BizNexus Hub", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Your go-to platform for smart networking, collaboration, and growth.",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate("dashboard") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Dashboard", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("My Profile", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("discover") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Discover", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("features") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Features", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("post") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Post Collaboration", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("contact") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("Contact Us", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("about") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(50)
            ) {
                Text("About", color = Color.White)
            }
        }
    }
}



@Composable
fun FeaturesScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Features", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Platform Features",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))
            FeatureItem("Smart Networking Suggestions", R.drawable.smart)
            FeatureItem("Freelance & Business Matching", R.drawable.matching)
            FeatureItem("Project Collaboration Tools", R.drawable.collaboration)
            FeatureItem("Event Discovery", R.drawable.event)
            FeatureItem("Verified Profiles", R.drawable.verify)
        }
    }
}

@Composable
fun FeatureItem(text: String, imageRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, color = Color.White, fontSize = 18.sp)
    }
}




@Composable
fun PostCollaborationScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Post Collaboration", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = details, onValueChange = { details = it }, label = { Text("Details") },
                modifier = Modifier.fillMaxWidth().height(120.dp), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* handle submit */ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                Text("Submit Post", color = Color.White)
            }
        }
    }
}



@Composable
fun ContactScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subscribe by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Contact", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Contact Us", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current.copy(color = Color.White))
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = subscribe, onCheckedChange = { subscribe = it })
                Text("Subscribe to our updates", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* send message */ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                Text("Submit", color = Color.White)
            }
        }

    }
}

