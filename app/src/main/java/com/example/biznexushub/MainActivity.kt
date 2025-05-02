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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo at top
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


@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("\u2190", color = Color.White, modifier = Modifier.clickable { navController.popBackStack() })
        }
        LogoHeader()
        Spacer(modifier = Modifier.height(8.dp))
        Text("Welcome to BizNexus Hub", fontSize = 20.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Your go-to platform for smart networking, collaboration, and growth.", color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("features") }, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), shape = RoundedCornerShape(50)) {
            Text("Features", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("post") }, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), shape = RoundedCornerShape(50)) {
            Text("Post Collaboration", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("contact") }, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), shape = RoundedCornerShape(50)) {
            Text("Contact Us", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("about") }, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), shape = RoundedCornerShape(50)) {
            Text("About", color = Color.White)
        }
    }
}

@Composable
fun FeaturesScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeader()
        Text("\u2190 Back", modifier = Modifier.clickable { navController.popBackStack() }, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Platform Features", fontSize = 20.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        FeatureItem("Smart Networking Suggestions", R.drawable.smart)
        FeatureItem("Freelance & Business Matching", R.drawable.matching)
        FeatureItem("Project Collaboration Tools", R.drawable.event)
        FeatureItem("Event Discovery", R.drawable.event)
        FeatureItem("Verified Profiles", R.drawable.verify)
    }
}

@Composable
fun FeatureItem(text: String, imageRes: Int) {
    Spacer(modifier = Modifier.height(8.dp))
    Image(painter = painterResource(id = imageRes), contentDescription = text, modifier = Modifier.size(100.dp))
    Text(text, color = Color.White)
}

@Composable
fun PostCollaborationScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeader()
        Text("\u2190 Back", modifier = Modifier.clickable { navController.popBackStack() }, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Post Collaboration", fontSize = 20.sp, color = Color.White)
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

@Composable
fun ContactScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subscribe by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeader()
        Text("\u2190 Back", modifier = Modifier.clickable { navController.popBackStack() }, color = Color.White)
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
