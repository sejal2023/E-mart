using System.ComponentModel.DataAnnotations;

namespace E_mart.Dtos
{
    public class UserDTO
    {
        [Required]
        [EmailAddress]
        public string Email { get; set; } = null!;

        [Required]
        [StringLength(255, MinimumLength = 6, ErrorMessage = "Password must be between 6 and 255 characters.")]
        public string Password { get; set; } = null!;
    }
}
